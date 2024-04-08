package com.metyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.metyou.common.Const;
import com.metyou.common.ServerResponse;
import com.metyou.dao.*;
import com.metyou.pojo.CommissionRecord;
import com.metyou.pojo.Sorder;
import com.metyou.pojo.StaffOrderRecord;
import com.metyou.service.ISuperviceOrderService;
import com.metyou.vo.OrderAndStaffStatusVO;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("iSuperviceOrderService")
public class SuperviseOrderServiceImpl implements ISuperviceOrderService {

    @Autowired
    private SorderMapper sorderMapper;

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private CommissionRecordMapper commissionRecordMapper;

    @Autowired
    private StaffOrderRecordMapper staffOrderRecordMapper;

    @Autowired
    private ScommodityMapper scommodityMapper;

    @Override
    public ServerResponse initStaffOrder(String creator, Integer creatorId) {
        //查询所有订单
        List<Sorder> sorderList = sorderMapper.getAllSorder();
        for (Sorder order : sorderList) {
            //根据监督员字段进行拆分
            String[] superviserNames = order.getSupervisName().split("\\+");
            //根据监督员姓名 获取id
            for (String name : superviserNames) {
                Integer staffId = staffMapper.selectByUsername(name);
                //根据commission_record 去查询这笔订单和监督员有无结算
                List<CommissionRecord> recordList = commissionRecordMapper.selectByOrderIdAndStaffId(order.getId(), staffId);
                StaffOrderRecord staffOrderRecord = new StaffOrderRecord();
                staffOrderRecord.setOrderId(order.getId());
                staffOrderRecord.setStaffId(staffId);
                staffOrderRecord.setStaffName(name);
                staffOrderRecord.setCreator(creator);
                staffOrderRecord.setCreatorId(creatorId);
                staffOrderRecord.setCreateTime(order.getCreateTime());
                if (recordList.isEmpty()) {
                    //没有结算 进行结算 insert
                    staffOrderRecord.setCommission(order.getCommission());
                    staffOrderRecord.setOrderStatus(order.getStatus());
                } else {
                    //说明状态有记录 判断数量 如果是1条 就说明是待结算 如果是两条 则说明 已结算，如果超过 说明数据异常
                    if (recordList.size() > 2) {
                        return ServerResponse.createByErrorMessage("数据异常" + order.getId());
                    }
                    if (recordList.size() == 1) {
                        //说明状态是待结算
                        staffOrderRecord.setCommission(recordList.get(0).getCommission());
                        staffOrderRecord.setOrderStatus(Const.OrderStatus.ORDER_STATUS_FINISHED);
                    }
                    if (recordList.size() == 2) {
                        //说明状态是已结算
                        staffOrderRecord.setCommission(recordList.get(0).getCommission());
                        staffOrderRecord.setOrderStatus(Const.OrderStatus.ORDER_STATUS_PAYED);
                    }

                }
                int row = staffOrderRecordMapper.insertSelective(staffOrderRecord);
                if (row == 0) {
                    return ServerResponse.createByErrorMessage("插入失败");
                }
            }
        }
        return ServerResponse.createBySuccess("初始化 staff_order_record 成功");
    }

    /**
     * 获取全部订单
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ServerResponse<PageInfo> getAllOrders(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Sorder> sorderList = sorderMapper.getAllSorder();
        ArrayList<OrderAndStaffStatusVO> orderAndStaffStatusVOList = new ArrayList<>();

        for(Sorder order : sorderList) {
            OrderAndStaffStatusVO orderAndStaffStatusVO = generatorOrderAndStaff(order);
            List<StaffOrderRecord> staffOrderRecordList = staffOrderRecordMapper.selectByOrderId(order.getId());
            orderAndStaffStatusVO.setStaffOrderRecordList(staffOrderRecordList);
            orderAndStaffStatusVOList.add(orderAndStaffStatusVO);
        }
        PageInfo pageResult = new PageInfo(sorderList);
        pageResult.setList(orderAndStaffStatusVOList);
        return ServerResponse.createBySuccess(pageResult);
    }

    private OrderAndStaffStatusVO generatorOrderAndStaff(Sorder sorder) {
        if (sorder == null)
            return null;
        OrderAndStaffStatusVO orderAndStaffStatusVO = new OrderAndStaffStatusVO();
        /**
         *  订单原始信息
         */
        orderAndStaffStatusVO.setId(sorder.getId());
        orderAndStaffStatusVO.setUserId(sorder.getUserId());
        orderAndStaffStatusVO.setUsername(sorder.getUsername());
        orderAndStaffStatusVO.setWechat(sorder.getWechat());
        orderAndStaffStatusVO.setOriginPrice(sorder.getOriginPrice());
        orderAndStaffStatusVO.setSalePrice(sorder.getSalePrice());
        orderAndStaffStatusVO.setBalance(sorder.getBalance());
        orderAndStaffStatusVO.setBeginTime(sorder.getBeginTime());
        orderAndStaffStatusVO.setCardId(sorder.getCardId());
        orderAndStaffStatusVO.setNote(sorder.getNote());
        orderAndStaffStatusVO.setEndTime(sorder.getEndTime());
        orderAndStaffStatusVO.setPayway(sorder.getPayway());
        orderAndStaffStatusVO.setCommodityId(sorder.getCommodityId());

        /**
         * 商品相关
         */
        String commodityName = scommodityMapper.selectByPrimaryKey(sorder.getCommodityId()).getName();
        orderAndStaffStatusVO.setCommodityName(commodityName);
        orderAndStaffStatusVO.setCommodityNum(sorder.getCommodityNum());

        return orderAndStaffStatusVO;
    }


}
