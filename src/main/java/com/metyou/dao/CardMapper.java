package com.metyou.dao;

import com.metyou.pojo.Card;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CardMapper {
    int insert(Card record);

    int insertSelective(Card record);

    List<Card>searchCard(@Param("userId") Integer userId, @Param("status") Integer status);
}