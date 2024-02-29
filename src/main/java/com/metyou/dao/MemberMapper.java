package com.metyou.dao;

import com.metyou.pojo.Member;

public interface MemberMapper {
    int insert(Member record);

    int insertSelective(Member record);
}