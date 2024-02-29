package com.metyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.metyou.common.Const;
import com.metyou.common.ResponseCode;
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
    public ServerResponse<PageInfo> getStaffList(Integer id, Integer role, Integer status, int pageNum, int pageSize, String username,Integer belong) {
        PageHelper.startPage(pageNum, pageSize);
        //开始判断逻辑，如果有id、role、status 搜索
        username = new StringBuilder().append("%").append(username).append("%").toString();
        List<Staff> staffList = staffMapper.selectByIdorRoleOrStatus(id, role, status, username, belong);
        PageInfo pageResult = new PageInfo(staffList);
        pageResult.setList(staffList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse changeStatus(Integer id, Integer status) {
        //开始查询监督员id
        if (id == null || status == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Integer rowCount = staffMapper.updateStatus(id, status);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("状态更新成功！");
        }
        return ServerResponse.createByErrorMessage("状态更新失败！");
    }

    @Override
    public ServerResponse updateImg(Integer id, String imgPath) {
        Integer rowCount = staffMapper.updateImage(id, imgPath);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("头像更新成功！");
        }
        return ServerResponse.createByErrorMessage("头像更新失败！");
    }

    @Override
    public ServerResponse updateStaffInfo(Staff staff) {
        int rowCount = staffMapper.updateByPrimaryKeySelective(staff);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("更新用户信息成功",staff);
        }
        return ServerResponse.createByErrorMessage("更新用户信息失败！");

    }

    @Override
    public ServerResponse addStaff(Staff staff) {
        int rowCount = staffMapper.insert(staff);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("新增员工成功！");
        }
        return ServerResponse.createByErrorMessage("新增员工失败!");
    }

    @Override
    public ServerResponse deleteStaff(Integer id) {
        int rowCount = staffMapper.deleteByPrimaryKey(id);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("删除员工成功！");
        }
        return ServerResponse.createByErrorMessage("删除员工失败!");
    }
}
