package com.metyou.dao;

import com.metyou.pojo.CommissionRecord;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface CommissionRecordMapper {
    int insert(CommissionRecord record);

    int insertSelective(CommissionRecord record);

    int selectByOrderIdAndOperator(@Param("orderId") Integer orderId, @Param("operator") String operator);

    BigDecimal payedCalculate(@Param("staffId") Integer staffId, @Param("operator") String operator);
}