package com.metyou.dao;

import com.metyou.pojo.CommissionRecord;
import org.apache.ibatis.annotations.Param;

public interface CommissionRecordMapper {
    int insert(CommissionRecord record);

    int insertSelective(CommissionRecord record);

    int selectByOrderIdAndOperator(@Param("orderId") Integer orderId, @Param("operator") String operator);
}