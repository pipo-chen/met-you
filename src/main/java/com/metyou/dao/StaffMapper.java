package com.metyou.dao;

import com.metyou.pojo.Staff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Staff record);

    Staff selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Staff record);

    int updateByPrimaryKey(Staff record);

    List<Staff> selectByIdorRoleOrStatus(@Param("id") Integer id, @Param("role") Integer role, @Param("status") Integer status, @Param("username") String username);

    int updateStatus(@Param("id") Integer id, @Param("status") Integer status);

    int updateImage(@Param("id") Integer id, @Param("mainImage") String mainImage);
}
