package com.metyou.service;

import com.github.pagehelper.PageInfo;
import com.metyou.common.ServerResponse;

public interface IStaffService {
    ServerResponse<PageInfo> getStaffList(Integer id, Integer role, Integer status, int pageNum, int pageSize);

}
