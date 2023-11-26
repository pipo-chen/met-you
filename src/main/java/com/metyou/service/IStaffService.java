package com.metyou.service;

import com.github.pagehelper.PageInfo;
import com.metyou.common.ServerResponse;
import com.metyou.pojo.Staff;

public interface IStaffService {
    ServerResponse<PageInfo> getStaffList(Integer id, Integer role, Integer status, int pageNum, int pageSize);

    ServerResponse changeStatus(Integer id, Integer status);

    ServerResponse updateImg(Integer id, String imgPath);

    ServerResponse updateStaffInfo(Staff staff);

    ServerResponse addStaff(Staff staff);

    ServerResponse deleteStaff(Integer id);
}
