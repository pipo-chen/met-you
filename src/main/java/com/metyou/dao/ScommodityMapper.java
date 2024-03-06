package com.metyou.dao;

import com.metyou.pojo.Scommodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScommodityMapper {
    int insert(Scommodity record);

    int insertSelective(Scommodity record);

    Scommodity selectByPrimaryKey(Integer id);
}