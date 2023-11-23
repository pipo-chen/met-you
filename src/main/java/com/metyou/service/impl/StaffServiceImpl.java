package com.metyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.metyou.common.ServerResponse;
import com.metyou.dao.StaffMapper;
import com.metyou.pojo.Staff;
import com.metyou.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("iStaffService")
public class StaffServiceImpl implements IStaffService {

    @Autowired
    private StaffMapper staffMapper;

    @Override
    public ServerResponse<PageInfo> getStaffList(Integer id, Integer role, Integer status, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //开始判断逻辑，如果有id、role、status 搜索
        List<Staff> staffList = staffMapper.selectByIdorRoleOrStatus(id, role, status);
        PageInfo pageResult = new PageInfo(staffList);
        pageResult.setList(staffList);
        return ServerResponse.createBySuccess(pageResult);
    }
}
