package com.metyou.service;

import com.github.pagehelper.PageInfo;
import com.metyou.common.ServerResponse;

public interface ISuperviceOrderService {
    //backend 订正现有的订单 初始化 staff_order_record table
    ServerResponse initStaffOrder(String creator, Integer creatorId);

    //backend 获取所有订单
    ServerResponse<PageInfo> getAllOrders(int pageNum, int pageSize);

}
