package com.metyou.dao;

import com.metyou.pojo.BalanceRecord;

public interface BalanceRecordMapper {
    int insert(BalanceRecord record);

    int insertSelective(BalanceRecord record);
}