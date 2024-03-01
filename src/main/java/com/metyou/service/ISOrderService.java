package com.metyou.service;

import com.metyou.common.ServerResponse;
import com.metyou.pojo.CardRecord;
import com.metyou.pojo.Sorder;
import com.metyou.vo.SuperviseOrderVO;

import java.util.List;

public interface ISOrderService {
    ServerResponse<List<SuperviseOrderVO>> searchOrderRecord(Integer userId, Integer cardId, Integer payway);

    ServerResponse consume(Sorder sorder, CardRecord record);
}
