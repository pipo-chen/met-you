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
import java.util.Arrays;
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

        Integer staffId = staffMapper.selectByUsername(supervisName);
        supervisName = new StringBuilder().append("%").append(supervisName).append("%").toString();

        List<Sorder> sorders = sorderMapper.search(supervisName, status);
        //通过sorders中的对象，挨个查询数据库中的监督员信息和商品信息
        List<SuperviseOrderVO>data = new ArrayList<>();

        //查找已经结算过的单子
        List<Integer>payedIds = commissionRecordMapper.selectPayedOrderId(staffId, "sub");

        if (status == Const.OrderStatus.ORDER_STATUS_PAYED) {
            for (Integer payedId : payedIds) {
                Sorder order = sorderMapper.selectByPrimaryKey(payedId);
                SuperviseOrderVO superviseOrderVO = sorderGenerateSuperviseOrderVO(order, staffId);
                data.add(superviseOrderVO);
            }
        } else {
            //查找没有结算过的单子，并且排除掉sub里面的,其中就包含可以操作到结算的单子
            for (Sorder order : sorders) {
                //如果改订单已经结算过了，就不要出现再这里了
                if (payedIds.contains(order.getId()))
                    continue;
                SuperviseOrderVO superviseOrderVO = sorderGenerateSuperviseOrderVO(order, staffId);
                data.add(superviseOrderVO);
            }
        }
        if (data.isEmpty()) {
            return ServerResponse.createByErrorMessage("没有查到相关服务记录");
        }
        return ServerResponse.createBySuccess(data);

    }

    /**
     * 监督员视角订单信息
     * @param order
     * @return
     */
    public SuperviseOrderVO sorderGenerateSuperviseOrderVO(Sorder order, Integer staffId) {
        /**
         * 多人情况下，如果是进行中的订单 commission 仅供参考
         * 如果当前订单已经关闭或者结算
         * 监督员的commission 展示 还是从 commission_record中根据 staff_id 和 order_id 去查
         */
        SuperviseOrderVO superviseOrderVO = new SuperviseOrderVO();
        Staff staff = staffMapper.selectByPrimaryKey(staffId);
        Scommodity scommodity =  scommodityMapper.selectByPrimaryKey(order.getCommodityId());

        //设置学员信息
        superviseOrderVO.setUserId(order.getUserId());
        superviseOrderVO.setUsername(order.getUsername());
        superviseOrderVO.setWechat(order.getWechat());

        if (order.getStatus() >= Const.OrderStatus.ORDER_STATUS_FINISHED) {
            //特殊情况下 commission 从 commission_record中取，因为那里存放着订正的数据
            List<CommissionRecord> commissionRecordList = commissionRecordMapper.selectByOrderIdAndStaffId(order.getId(), staffId);
            if (commissionRecordList.size() > 0) {
                BigDecimal commission = commissionRecordList.get(0).getCommission();
                superviseOrderVO.setCommission(commission);
            }
        } else {
            superviseOrderVO.setCommission(order.getCommission());
        }

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
        superviseOrderVO.setCreateTime(order.getCreateTime());

        //获取监督员姓名
        superviseOrderVO.setStaffName(staff.getUsername());
        superviseOrderVO.setSupervisName(order.getSupervisName());

        //设置商品信息
        superviseOrderVO.setCommodityId(order.getCommodityId());
        superviseOrderVO.setCommodityName(scommodity.getName());
        return superviseOrderVO;
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
        //如果想设置成payed必须commission_record中的sub和监督员总数差1
        Integer staffId = staffMapper.selectByUsername(record.getCreator());
        if (status == Const.OrderStatus.ORDER_STATUS_PAYED) {
            List<Integer>payedIds = commissionRecordMapper.selectPayedOrderId(staffId, "sub");
            //增加佣金,佣金额度
            Sorder sorder = sorderMapper.selectByPrimaryKey(id);
            //判断有几位监督员
            String[] superviss = sorder.getSupervisName().split("\\+");
            if(payedIds.size() != superviss.length - 1) {
                //说明不能设置成payed
                status = Const.OrderStatus.ORDER_STATUS_FINISHED;
                //判断是否需要单独结算？只要看该订单id 有无被该监督员 sub 如果有的话则不能，如果没有则可以继续
                int rowCount = commissionRecordMapper.selectItemPayed(id, staffId, "sub");
                if (rowCount == 0) {
                    //说明可以添加结算
                    return addPayedRecord(id, record, staffId);
                }
            }
        }

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
                //如果此人之前这个订单从未被结算
                row = commissionRecordMapper.selectItemPayed(id, staffId, "sub");
                if (row == 0) {
                    return addPayedRecord(id, record, staffId);
                }
            }
        }
        return ServerResponse.createBySuccessMessage("操作成功！");
    }

    public ServerResponse<String> addPayedRecord(Integer orderId, CommissionRecord record, Integer staffId) {
        //减少结算佣金，佣金额度变化
        Sorder sorder = sorderMapper.selectByPrimaryKey(orderId);
        //todo getcommission方式不对如果是结算的话，从add中进行扣出 找到对应的add记录获取对应的commission
        List<CommissionRecord> recordList = commissionRecordMapper.selectByOrderIdAndStaffId(orderId, staffId);
        if (recordList.size() == 0) {
            return ServerResponse.createByErrorMessage("该订单还没有结束");
        } else {
            BigDecimal commission = recordList.get(0).getCommission();
            record.setCommission(sorder.getCommission());
        }
        record.setStaffId(staffId);
        record.setStaffName(record.getCreator());
        record.setOperator("sub");
        int rowCount = commissionRecordMapper.insertSelective(record);
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
        return ServerResponse.createBySuccessMessage("操作成功！");
    }

    /**
     * 仅超级管理员可以使用的佣金订正(sorder中的佣金字段，查询涉及到的 commission_record -> staffId 如果该行是sub 则balance- 是add则用户balance+)
     * @param orderId
     * @param commission
     * @return
     */
    @Override
    public ServerResponse<String> correctCommission(Integer orderId, BigDecimal commission) {
        int rowCount = sorderMapper.updateCommission(orderId, commission);
        if (rowCount > 0) {
            //操作成功开始根据order_id 找到commission_record
            List<CommissionRecord> commissionRecordList = commissionRecordMapper.selectByOrderId(orderId);
            for (CommissionRecord record : commissionRecordList) {
                //根据每条record 更新commission - 同时根据staffId 和 operator 更新balance
                Integer staffId = record.getStaffId();
                Staff staff = staffMapper.selectByPrimaryKey(staffId);
                BigDecimal balance = staff.getBalance();
                BigDecimal oldCommission = record.getCommission();
                //先复原balance
                if (record.getOperator().equals("sub")) {
                    balance = balance.add(oldCommission);
                    BigDecimal newBalance = balance.subtract(commission);
                    if (!updateCommission(staffId, record.getId(), newBalance, commission)) {
                        return ServerResponse.createByErrorMessage("佣金更新失败");
                    }
                }
                else if (record.getOperator().equals("add")) {
                    balance = balance.subtract(oldCommission);
                    BigDecimal newBalance  = balance.add(commission);
                    if (!updateCommission(staffId, record.getId(), newBalance, commission)) {
                        return ServerResponse.createByErrorMessage("佣金更新失败");
                    }
                }
            }
        }
        return ServerResponse.createBySuccess("更新成功！");
    }

    private Boolean updateCommission(Integer staffId, Integer recordId, BigDecimal newBalance, BigDecimal commission) {
        int row = staffMapper.updateBalance(staffId, newBalance);
        if (row <= 0) {
            return false;
        }
        //根据 ID 去更新 commission_record
        row = commissionRecordMapper.updateCommission(recordId, commission);
        if (row <= 0) {
            return false;
        }
        return true;
    }

    /**
     * 仅供超级管理员使用的佣金更新（这种更新不会同步更新 sorder 中的 commission， 多人监督 会出现一个标价佣金，和实际佣金的出入）
     * @param commissionRecord中的id
     * @param commission 变更后的佣金
     * @return
     */
    @Override
    public ServerResponse<String> correctPerCommission(String staffName, Integer orderId, BigDecimal commission) {
        Integer staffId = staffMapper.selectByUsername(staffName);

        if (staffId == null) {
            return ServerResponse.createByErrorMessage("该员工不存在");
        }
        //根据每条record 更新commission - 同时根据staffId 和 operator 更新balance
        //根据staffId 和 orderId 查询对应的record
        List<CommissionRecord> recordList = commissionRecordMapper.selectByOrderIdAndStaffId(orderId, staffId);
        //把它内部所有的记录，都做订正
        for (CommissionRecord record : recordList) {
            Staff staff = staffMapper.selectByPrimaryKey(staffId);
            BigDecimal balance = staff.getBalance();
            BigDecimal oldCommission = record.getCommission();
            //先复原balance
            if (record.getOperator().equals("sub")) {
                balance = balance.add(oldCommission);
                BigDecimal newBalance = balance.subtract(commission);
                if (!updateCommission(staffId, record.getId(), newBalance, commission)) {
                    return ServerResponse.createByErrorMessage("佣金更新失败");
                }
            } else if (record.getOperator().equals("add")) {
                balance = balance.subtract(oldCommission);
                BigDecimal newBalance = balance.subtract(oldCommission);
                if (!updateCommission(staffId, record.getId(), newBalance, commission)) {
                    return ServerResponse.createByErrorMessage("佣金更新失败");
                }
            }
        }
        return ServerResponse.createBySuccess("更新成功！");
    }
}
