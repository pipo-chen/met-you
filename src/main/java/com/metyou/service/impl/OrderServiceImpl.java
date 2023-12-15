package com.metyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.metyou.common.ServerResponse;
import com.metyou.dao.OrderItemMapper;
import com.metyou.dao.OrderMapper;
import com.metyou.pojo.Order;
import com.metyou.pojo.OrderItem;
import com.metyou.service.IOrderService;
import com.metyou.vo.OrderProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iOrderService")
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public ServerResponse queryOrderPayStatus(Integer userId, Long orderNo) {
        Order order = orderMapper.selectByUserIdAndOrderNo(userId, orderNo);
        if (order == null) {
            return ServerResponse.createByErrorMessage("用户没有该订单");
        }
        //判断订单是否已经支付
        return ServerResponse.createBySuccess(order);
    }

    @Override
    public ServerResponse<List<OrderProductVO>> findAllOrdersAndRelateInfo() {
        List<OrderProductVO> orderProductVOList = orderMapper.findAllOrderRelate();
        //找到的 orderProductVOList 还需要根据orderNo规整一下map？
        for (OrderProductVO item : orderProductVOList) {
            //获得的每个 item 再根据字典去组合 productList

        }
        return ServerResponse.createBySuccess(orderProductVOList);
    }

    @Override
    public ServerResponse orderList(Integer userId, Integer status,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orderList = orderMapper.searchOrderByUserIdOrStatus(userId, status);
        PageInfo pageResult = new PageInfo(orderList);
        return ServerResponse.createBySuccess("查询成功", pageResult);

    }

    @Override
    public ServerResponse orderItemList(Long orderNo) {
        //从orderItemMapper 中选择 orderNo
        List<OrderItem> orderItemList = orderItemMapper.selectByOrderNo(orderNo);
        return ServerResponse.createBySuccess("查询成功", orderItemList);
    }
}
