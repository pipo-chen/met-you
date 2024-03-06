package com.metyou.service;

import com.metyou.common.ServerResponse;

import java.math.BigDecimal;

public interface IRecordCommissionService {
    ServerResponse<String> totalPayed(String staffName);

    ServerResponse<String> totalUnPayed(String staffName);
}
