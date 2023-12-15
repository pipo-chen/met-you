package com.metyou.dao;

import com.metyou.pojo.Order;
import com.metyou.vo.OrderProductVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<OrderProductVO>findAllOrderRelate();

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    Order selectByUserIdAndOrderNo(@Param("userId") Integer userId, @Param("orderNo")Long orderNo);

    List<Order> findAllOrder();

    List<Order> searchOrderByUserIdOrStatus(@Param("userId") Integer userId, @Param("status")Integer status);
}