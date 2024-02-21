package com.metyou.dao;

import com.metyou.pojo.PointRecord;

public interface PointRecordMapper {
    int insert(PointRecord record);

    int insertSelective(PointRecord record);
}