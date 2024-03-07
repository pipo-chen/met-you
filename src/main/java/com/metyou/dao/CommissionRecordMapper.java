package com.metyou.dao;

import com.metyou.pojo.CommissionRecord;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface CommissionRecordMapper {
    int insert(CommissionRecord record);

    int insertSelective(CommissionRecord record);

    int selectByOrderIdAndOperator(@Param("orderId") Integer orderId, @Param("operator") String operator);

    BigDecimal payedCalculate(@Param("staffId") Integer staffId, @Param("operator") String operator);

    List<Integer> selectPayedOrderId(@Param("staffId") Integer staffId, @Param("operator")String operator);

    int selectItemPayed(@Param("orderId") Integer orderId, @Param("staffId") Integer staffId, @Param("operator") String operator);

    List<CommissionRecord>selectByOrderId(@Param("orderId") Integer orderId);

    int updateCommission(@Param("id") Integer id, @Param("commission") BigDecimal commission);

    CommissionRecord selectRecord(@Param("id")Integer id);

    List<CommissionRecord>selectByOrderIdAndStaffId(@Param("orderId") Integer orderId, @Param("staffId") Integer staffId);
}