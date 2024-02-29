package com.metyou.dao;

import com.metyou.pojo.Scommodity;

public interface ScommodityMapper {
    int insert(Scommodity record);

    int insertSelective(Scommodity record);

    Scommodity selectByPrimaryKey(Integer id);
}