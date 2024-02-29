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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping("/manage/staff")
public class StaffController {

    @Autowired
    private IStaffService iStaffService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IFileService iFileService;

    /**
     * 查询监督员的列表（支持通过 name 模糊搜索）
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
                                         @RequestParam(value = "belong", defaultValue = "1") Integer belong,
                                         @RequestParam(value = "username",required = false) String username,
                                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return iStaffService.getStaffList(staffId, role, status, pageNum, pageSize,username,belong);
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
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }

        if (iUserService.checkAdminRole(user).isSuccess()) {
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
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            return iStaffService.updateStaffInfo(staff);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    /**
     * 点击头像后，上传头像修改接口
     * @param session
     * @param staff
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("update_all_info")
    @ResponseBody
    ServerResponse updateAllStaffInfo(HttpSession session, Staff staff, @RequestParam(value = "image", required = false)MultipartFile file, HttpServletRequest request) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFileName = iFileService.upload(file, path);
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;

            Map fileMap = Maps.newHashMap();
            fileMap.put("uri", targetFileName);
            fileMap.put("url", url);
            //再把targetFileName 存入数据库
            staff.setMainImage(targetFileName);
            //上传新头像之后，旧头像删除掉 旧头像地址
            System.out.println("-->"+targetFileName);
            return iStaffService.updateStaffInfo(staff);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping("add_staff")
    @ResponseBody
    ServerResponse addStaff(HttpSession session, Staff staff) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            return iStaffService.addStaff(staff);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping("add_all_info")
    @ResponseBody
    ServerResponse addAllStaffInfo(HttpSession session, Staff staff, @RequestParam(value = "image", required = false)MultipartFile file, HttpServletRequest request) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            String path = request.getSession().getServletContext().getRealPath("upload");
            String targetFileName = iFileService.upload(file, path);
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;

            Map fileMap = Maps.newHashMap();
            fileMap.put("uri", targetFileName);
            fileMap.put("url", url);
            staff.setMainImage(targetFileName);
            return iStaffService.addStaff(staff);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping("delete_staff")
    @ResponseBody
    public ServerResponse deleteStaff(HttpSession session, Integer staffId) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            return iStaffService.deleteStaff(staffId);

        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }
}
