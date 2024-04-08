package com.metyou.dao;

import com.metyou.pojo.StaffOrderRecord;
import com.metyou.pojo.StaffOrderRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffOrderRecordMapper {
    long countByExample(StaffOrderRecordExample example);

    int insert(StaffOrderRecord record);

    int insertSelective(StaffOrderRecord record);

    List<StaffOrderRecord> selectByExample(StaffOrderRecordExample example);

    List<StaffOrderRecord> selectByOrderId(@Param("orderId") Integer orderId);
}