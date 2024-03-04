package com.metyou.service.impl;

import com.metyou.common.ServerResponse;
import com.metyou.dao.*;
import com.metyou.pojo.*;
import com.metyou.service.ISOrderService;
import com.metyou.vo.SuperviseOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    @Autowired
    private CardMapper cardMapper;

    @Autowired
    private CardRecordMapper cardRecordMapper;

    @Autowired
    private UserMapper userMapper;

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
            superviseOrderVO.setCommodityNum(order.getCommodityNum());
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

    /**
     * 后台，根据监督员查询订单及佣金、开始时间、结束时间
     * @param supervisName
     * @return
     */
    @Override
    public ServerResponse<List<SuperviseOrderVO>> search(String supervisName) {
        supervisName = new StringBuilder().append("%").append(supervisName).append("%").toString();
        List<Sorder> sorders = sorderMapper.search(supervisName);
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
            superviseOrderVO.setCommodityNum(order.getCommodityNum());
            superviseOrderVO.setCommission(order.getCommission());

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
            return ServerResponse.createByErrorMessage("没有查到相关服务记录");
        }
        return ServerResponse.createBySuccess(data);

    }

    @Override
    public ServerResponse<String> consume(Sorder sorder, CardRecord record) {
        //userId, commodityId, supervisId, supervisName, salePrice, payway, cardId, note
        //根据传入的commodityId 查询当前商品的销售价格
        Scommodity scommodity = scommodityMapper.selectByPrimaryKey(sorder.getCommodityId());
        sorder.setOriginPrice(scommodity.getPrcie());

        //根据传入的userId填写购买时用户名和wechat
        User user = userMapper.selectByPrimaryKey(sorder.getUserId());
        sorder.setUsername(user.getUsername());
        sorder.setWechat(user.getWechat());

        //输入监督员姓名之后，id就不用手动输入了,通过名字查id
        String names = sorder.getSupervisName();
        String[] namelist = names.split("\\+");
        int staff_id = staffMapper.selectByUsername(namelist[0]);
        if (staff_id < 0) {
            return ServerResponse.createByErrorMessage("监督员不存在!");
        }
        sorder.setSupervisId(staff_id);

        //设置余额
        if (sorder.getPayway() == 4 ) {
            //新增一条到cardrecord 表中
            int rowCount = cardRecordMapper.insertSelective(record);
            record.setNote("消费时系统自动创建");

            if (rowCount <= 0) {
                return ServerResponse.createByErrorMessage("新增会员消费项失败!");
            }
            Card card = cardMapper.selectByPrimaryKey(sorder.getCardId());
            //现有的余额都从卡里面取出
            if (card.getBalance().floatValue() < 1 || card.getBalance().floatValue() - sorder.getSalePrice().floatValue() < 1)
                return ServerResponse.createByErrorMessage("余额不足!");
            else {
                BigDecimal balance = card.getBalance().subtract(sorder.getSalePrice());
                sorder.setBalance(balance);
                //同样，那张卡也需要进行余额扣除的变更
                card.setBalance(balance);
                rowCount = cardMapper.updateBalance(card);
                if (rowCount <= 0) {
                    return ServerResponse.createBySuccess("余额变更失败!");
                }
            }
        }
        //新建订单都是待开始状态
        sorder.setStatus(1);
        //开始插入新订单
        int rowCount = sorderMapper.insertSelective(sorder);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("订单创建成功!");
        }
        return ServerResponse.createByErrorMessage("订单创建失败!");
    }
}
