package com.metyou.service.impl;

import com.metyou.common.ServerResponse;
import com.metyou.dao.OrderMapper;
import com.metyou.pojo.Order;
import com.metyou.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iOrderService")
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public ServerResponse queryOrderPayStatus(Integer userId, Long orderNo) {
        Order order = orderMapper.selectByUserIdAndOrderNo(userId, orderNo);
        if (order == null) {
            return ServerResponse.createByErrorMessage("用户没有该订单");
        }
        //判断订单是否已经支付
        return ServerResponse.createBySuccess(order);
    }
}
