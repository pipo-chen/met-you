package com.metyou.dao;

import com.metyou.pojo.Card;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CardMapper {
    int insert(Card record);

    int insertSelective(Card record);

    Card selectByPrimaryKey(Integer id);

    List<Card>searchCard(@Param("userId") Integer userId, @Param("status") Integer status);

    List<Card>searchCardByIdOrWechat(@Param("userId") Integer userId, @Param("status") Integer status, @Param("wechat") String wechat);

    int updateBalance(Card record);
}