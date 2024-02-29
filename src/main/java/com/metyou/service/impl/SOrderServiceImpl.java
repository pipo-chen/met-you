package com.metyou.service.impl;

import com.metyou.common.ServerResponse;
import com.metyou.dao.ScommodityMapper;
import com.metyou.dao.SorderMapper;
import com.metyou.dao.StaffMapper;
import com.metyou.pojo.Scommodity;
import com.metyou.pojo.Sorder;
import com.metyou.pojo.Staff;
import com.metyou.service.ISOrderService;
import com.metyou.vo.SuperviseOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("ISOrderService")
public class SOrderServiceImpl implements ISOrderService {
    @Autowired
    private SorderMapper sorderMapper;

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private ScommodityMapper scommodityMapper;

    @Override
    public ServerResponse<List<SuperviseOrderVO>> searchOrderRecord(Integer userId, Integer cardId, Integer payway) {
        List<Sorder> sorders = sorderMapper.searchSorder(userId, cardId,payway);
        //通过sorders中的对象，挨个查询数据库中的监督员信息和商品信息
        List<SuperviseOrderVO>data = new ArrayList<>();

        for (Sorder order : sorders) {
            SuperviseOrderVO superviseOrderVO = new SuperviseOrderVO();
            Staff staff = staffMapper.selectByPrimaryKey(order.getSupervisId());
            Scommodity scommodity =  scommodityMapper.selectByPrimaryKey(order.getCommodityId());

            superviseOrderVO.setBalance(order.getBalance());
            superviseOrderVO.setCardId(order.getCardId());
            //产品定价.直接从商品中拿
            superviseOrderVO.setOriginPrice(scommodity.getPrcie());
            superviseOrderVO.setSalePrice(order.getSalePrice());
            //订单号
            superviseOrderVO.setId(order.getId());
            superviseOrderVO.setNote(order.getNote());
            superviseOrderVO.setPayway(order.getPayway());
            superviseOrderVO.setStatus(order.getStatus());
            superviseOrderVO.setBeginTime(order.getBeginTime());
            superviseOrderVO.setEndTime(order.getEndTime());
            superviseOrderVO.setBeginTime(order.getBeginTime());
            superviseOrderVO.setSupervisId(order.getSupervisId());
            superviseOrderVO.setStaffImg(staff.getMainImage());
            //获取监督员姓名
            superviseOrderVO.setStaffName(staff.getUsername());
            superviseOrderVO.setSupervisName(staff.getUsername());
            superviseOrderVO.setUserId(order.getUserId());

            superviseOrderVO.setCommodityId(order.getCommodityId());
            superviseOrderVO.setCommodityName(scommodity.getName());
            superviseOrderVO.setCreateTime(order.getCreateTime());
            data.add(superviseOrderVO);
        }
        if (sorders.isEmpty()) {
            return ServerResponse.createByErrorMessage("没有查到相关消费记录");
        }
        return ServerResponse.createBySuccess(data);
    }
}
