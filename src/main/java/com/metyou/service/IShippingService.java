package com.metyou.service;

import com.github.pagehelper.PageInfo;
import com.metyou.common.ServerResponse;
import com.metyou.pojo.Shipping;

public interface IShippingService {
    ServerResponse add(Integer userId, Shipping shipping);

    ServerResponse<String> del(Integer userId, Integer shippingId);

    ServerResponse update(Integer userId, Shipping shipping);

    ServerResponse select(Integer userId, Integer shippingId);

    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);
}
