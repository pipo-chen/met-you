package com.metyou.controller.backend;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.metyou.common.Const;
import com.metyou.common.ResponseCode;
import com.metyou.common.ServerResponse;
import com.metyou.pojo.Staff;
import com.metyou.pojo.User;
import com.metyou.service.IFileService;
import com.metyou.service.IStaffService;
import com.metyou.service.IUserService;
import com.metyou.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/manager/staff")
public class StaffController {

    @Autowired
    private IStaffService iStaffService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IFileService iFileService;

    /**
     * 查询监督员的列表
     * @param staffId
     * @param role
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public ServerResponse<PageInfo> list(@RequestParam(value = "staffId", required = false) Integer staffId,
                                         @RequestParam(value = "role", required = false) Integer role,
                                         @RequestParam(value = "status", required = false) Integer status,
                                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return iStaffService.getStaffList(staffId, role, status, pageNum, pageSize);
    }

    /**
     * 更新员工工作状态
     * @param staffId 传入员工id
     * @param status 传入状态
     * @return
     */
    @RequestMapping("change_status")
    @ResponseBody
    public ServerResponse changeStatus(HttpSession session, Integer staffId, Integer status) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        //todo: user == null
        if (false) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        //todo: iUserService.checkAdminRole(user).isSuccess()
        if (true) {
            return iStaffService.changeStatus(staffId, status);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    /**
     * 修改员工信息
     * @param session
     * @param staff
     * @return
     */
    @RequestMapping("update_staff_info")
    @ResponseBody
    ServerResponse updateStaffInfo(HttpSession session, Staff staff) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        //todo: user == null
        if (false) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        //todo: iUserService.checkAdminRole(user).isSuccess()
        if (true) {
            return iStaffService.updateStaffInfo(staff);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping("add_staff")
    @ResponseBody
    ServerResponse addStaff(HttpSession session, Staff staff) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        //todo: user == null
        if (false) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        //todo: iUserService.checkAdminRole(user).isSuccess()
        if (true) {
            return iStaffService.addStaff(staff);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    /**
     * 上传图片，并返回图片地址(未测试)
     * @param session
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("add_img")
    @ResponseBody
    public ServerResponse addImg(HttpSession session, @RequestParam(value = "upload_file", required = false)MultipartFile file, HttpServletRequest request) {

        User user = (User)session.getAttribute(Const.CURRENT_USER);
        //todo： user == null
        if (false) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        //todo：iUserService.checkAdminRole(user).isSuccess()
        if (true) {

            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFileName = iFileService.upload(file, path);
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;

            Map fileMap = Maps.newHashMap();
            fileMap.put("uri", targetFileName);
            fileMap.put("url", url);
            //再把targetFileName 存入数据库
            return ServerResponse.createBySuccess(fileMap);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }
    @RequestMapping("delete_staff")
    @ResponseBody
    public ServerResponse deleteStaff(HttpSession session, Integer staffId) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        //todo： user == null
        if (false) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        //todo：iUserService.checkAdminRole(user).isSuccess()
        if (true) {
            return iStaffService.deleteStaff(staffId);

        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }
}
