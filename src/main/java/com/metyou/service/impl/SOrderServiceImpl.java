package com.metyou.service.impl;

import com.metyou.common.Const;
import com.metyou.common.ServerResponse;
import com.metyou.dao.*;
import com.metyou.pojo.*;
import com.metyou.service.ISOrderService;
import com.metyou.vo.SuperviseOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private CommissionRecordMapper commissionRecordMapper;

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
            superviseOrderVO.setSupervisName(order.getSupervisName());
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
    public ServerResponse<List<SuperviseOrderVO>> search(String supervisName, Integer status) {
        supervisName = new StringBuilder().append("%").append(supervisName).append("%").toString();
        List<Sorder> sorders = sorderMapper.search(supervisName, status);
        //通过sorders中的对象，挨个查询数据库中的监督员信息和商品信息
        List<SuperviseOrderVO>data = new ArrayList<>();

        for (Sorder order : sorders) {
            //如果改订单已经结算过了，就不要出现再这里了
            if (order.getStatus() == Const.OrderStatus.ORDER_STATUS_PAYED) {
                continue;
            }
            SuperviseOrderVO superviseOrderVO = new SuperviseOrderVO();
            Staff staff = staffMapper.selectByPrimaryKey(order.getSupervisId());
            Scommodity scommodity =  scommodityMapper.selectByPrimaryKey(order.getCommodityId());

            //设置用户信息
            superviseOrderVO.setUserId(order.getUserId());
            superviseOrderVO.setUsername(order.getUsername());
            superviseOrderVO.setWechat(order.getWechat());

            //销售价格
            superviseOrderVO.setSalePrice(order.getSalePrice());

            //订单号
            superviseOrderVO.setId(order.getId());
            superviseOrderVO.setStatus(order.getStatus());
            superviseOrderVO.setBeginTime(order.getBeginTime());
            superviseOrderVO.setEndTime(order.getEndTime());
            superviseOrderVO.setSupervisId(order.getSupervisId());
            superviseOrderVO.setStaffImg(staff.getMainImage());
            superviseOrderVO.setCommodityNum(order.getCommodityNum());
            superviseOrderVO.setCommission(order.getCommission());
            superviseOrderVO.setCreateTime(order.getCreateTime());

            //获取监督员姓名
            superviseOrderVO.setStaffName(staff.getUsername());
            superviseOrderVO.setSupervisName(order.getSupervisName());

            //设置商品信息
            superviseOrderVO.setCommodityId(order.getCommodityId());
            superviseOrderVO.setCommodityName(scommodity.getName());

            data.add(superviseOrderVO);
        }
        if (sorders.isEmpty()) {
            return ServerResponse.createByErrorMessage("没有查到相关服务记录");
        }
        return ServerResponse.createBySuccess(data);

    }

    @Override
    public ServerResponse<String> consume(Sorder sorder, CardRecord record, boolean isOld) {
        //userId, commodityId, supervisId, supervisName, salePrice, payway, cardId, note
        //根据传入的commodityId 查询当前商品的销售价格
        Scommodity scommodity = scommodityMapper.selectByPrimaryKey(sorder.getCommodityId());
        //根据是否是续约单，再创建的时候，自动补全佣金
        if(isOld)
            sorder.setCommission(scommodity.getOldbuy());
        else
            sorder.setCommission(scommodity.getCommission());

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
        sorder.setStatus(Const.OrderStatus.ORDER_STATUS_NEED_BEGIN);
        //开始插入新订单
        int rowCount = sorderMapper.insertSelective(sorder);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("订单创建成功!");
        }
        return ServerResponse.createByErrorMessage("订单创建失败!");
    }

    @Override
    public ServerResponse<String> changeStatus(Integer id, Integer status, Date beginTime, Date endTime, CommissionRecord record) {
        int rowCount = sorderMapper.updateStatus(id, status, beginTime, endTime);
        //说明更新成功
        if(rowCount > 0) {
            //并且将结果同步到staff的balance中
            //判断如果订单状态是,已结束，则增加到监督员佣金列表，并且，该订单从未被增加过, 所以 order_id 必须是只加一次
            if (status == Const.OrderStatus.ORDER_STATUS_FINISHED) {
                int row = commissionRecordMapper.selectByOrderIdAndOperator(id, "add");
                //说明该订单从未被计入佣金
                //如果这个订单从未被添加入数据库中 那么就开始一单多人/一单一人的处理计算
                if (row == 0) {
                    //增加佣金,佣金额度
                    Sorder sorder = sorderMapper.selectByPrimaryKey(id);
                    //判断有几位监督员
                    String[] superviss = sorder.getSupervisName().split("\\+");

                    for (String per_supervis : superviss) {
                        CommissionRecord res = new CommissionRecord();
                        res.setOrderId(record.getOrderId());
                        res.setCreator(record.getCreator());
                        res.setCreatorId(record.getCreatorId());
                        res.setCommission(sorder.getCommission());
                        //根据员工名，查询员工ID
                        Integer per_supervis_id = staffMapper.selectByUsername(per_supervis);
                        res.setStaffId(per_supervis_id);
                        res.setStaffName(sorder.getSupervisName());
                        res.setOperator("add");
                        rowCount = commissionRecordMapper.insertSelective(res);
                        if (rowCount <= 0) {
                            return ServerResponse.createByErrorMessage("佣金增加失败!");
                        }
                        //获取员工当前的balance 加上commission 进行更新
                        Staff staff = staffMapper.selectByPrimaryKey(per_supervis_id);
                        BigDecimal balance = staff.getBalance().add(sorder.getCommission());
                        staff.setBalance(balance);
                        rowCount = staffMapper.updateBalance(per_supervis_id, balance);
                        if (rowCount <= 0) {
                            return ServerResponse.createByErrorMessage("佣金增加失败!");
                        }
                    }
                }
            }
            else if (status == Const.OrderStatus.ORDER_STATUS_PAYED) {
                //todo 在结算前，订单必须要先完成吗,对的
                int row = commissionRecordMapper.selectByOrderIdAndOperator(id, "add");
                //先判断一下订单有无增加过佣金
                if( row == 0) {
                    return ServerResponse.createByErrorMessage("必须先结束订单，才可以结算");
                }

                row = commissionRecordMapper.selectByOrderIdAndOperator(id, "sub");
                if (row == 0) {
                    //减少结算佣金，佣金额度变化
                    Sorder sorder = sorderMapper.selectByPrimaryKey(id);
                    record.setCommission(sorder.getCommission());
                    //todo 结算的话结算当前监督员的单子，不要结算订单中列的id
                    Integer staffId = staffMapper.selectByUsername(record.getCreator());
                    record.setStaffId(staffId);
                    record.setStaffName(record.getCreator());
                    record.setOperator("sub");
                    rowCount = commissionRecordMapper.insertSelective(record);
                    if (rowCount <= 0) {
                        return ServerResponse.createByErrorMessage("佣金减少失败!");
                    }
                    //获取员工当前的balance 减去本单 commission 进行更新
                    Staff staff = staffMapper.selectByPrimaryKey(staffId);
                    BigDecimal balance = staff.getBalance().subtract(sorder.getCommission());
                    staff.setBalance(balance);
                    rowCount = staffMapper.updateBalance(staffId, balance);
                    if (rowCount <= 0) {
                        return ServerResponse.createByErrorMessage("佣金减少失败!");
                    }
                }
            }
        }
        return ServerResponse.createBySuccessMessage("操作成功！");
    }
}
