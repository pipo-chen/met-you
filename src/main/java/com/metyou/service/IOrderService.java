package com.metyou.service;

import com.github.pagehelper.PageInfo;
import com.metyou.common.ServerResponse;
import com.metyou.vo.OrderProductVO;

import java.util.List;

public interface IOrderService {
    ServerResponse queryOrderPayStatus(Integer userId, Long orderNo);

    ServerResponse<List<OrderProductVO>> findAllOrdersAndRelateInfo();

    ServerResponse<PageInfo> orderList(Integer userId, Integer status, int pageNum, int pageSize);

    ServerResponse orderItemList(Long orderNo);

}
