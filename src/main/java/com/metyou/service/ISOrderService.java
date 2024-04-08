package com.metyou.service;

import com.metyou.common.ServerResponse;
import com.metyou.pojo.CardRecord;
import com.metyou.pojo.CommissionRecord;
import com.metyou.pojo.Sorder;
import com.metyou.vo.SuperviseOrderVO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ISOrderService {
    ServerResponse<List<SuperviseOrderVO>>searchOrderRecord(Integer userId, Integer cardId, Integer payway);

    ServerResponse<String>consume(Sorder sorder, CardRecord record, boolean isOld);

    ServerResponse<List<SuperviseOrderVO>>search(String supervisName, Integer status);

    ServerResponse<String>changeStatus(Integer id, Integer status, Date beginTime, Date endTime, CommissionRecord record);

    ServerResponse<String>correctCommission(Integer orderId, BigDecimal commission);

    ServerResponse<String>correctPerCommission(String staffName, Integer orderId, BigDecimal commission);

}
