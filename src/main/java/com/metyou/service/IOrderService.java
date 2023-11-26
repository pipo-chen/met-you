package com.metyou.service;

import com.metyou.common.ServerResponse;

public interface IOrderService {
    ServerResponse queryOrderPayStatus(Integer userId, Long orderNo);

}
