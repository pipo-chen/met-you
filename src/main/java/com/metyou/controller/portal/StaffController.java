package com.metyou.controller.portal;

import com.github.pagehelper.PageInfo;
import com.metyou.common.ServerResponse;
import com.metyou.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/staff/")
public class StaffController {

    @Autowired
    private IStaffService iStaffService;

    @RequestMapping("list")
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "id", required = false) Integer id,
                                         @RequestParam(value = "role", required = false) Integer role,
                                         @RequestParam(value = "status",defaultValue = "1") Integer status,
                                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return iStaffService.getStaffList(id, role, status, pageNum, pageSize);
    }

}
