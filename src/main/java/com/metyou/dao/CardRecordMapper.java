package com.metyou.dao;

import com.metyou.pojo.CardRecord;

public interface CardRecordMapper {
    int insert(CardRecord record);

    int insertSelective(CardRecord record);

}