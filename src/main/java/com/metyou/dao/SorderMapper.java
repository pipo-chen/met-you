package com.metyou.dao;

import com.metyou.pojo.Sorder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SorderMapper {
    int insert(Sorder record);

    int insertSelective(Sorder record);

    List<Sorder> searchSorder(@Param("userId") Integer userId, @Param("cardId") Integer cardId, @Param("payway") Integer payway);

    List<Sorder> search(@Param("supervisName") String supervisName);
}