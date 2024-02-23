package com.metyou.controller.backend;

import com.github.pagehelper.PageInfo;
import com.metyou.common.Const;
import com.metyou.common.ResponseCode;
import com.metyou.common.ServerResponse;
import com.metyou.pojo.User;
import com.metyou.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/user")
public class UserManagerController {
    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        ServerResponse<User> response = iUserService.login(username, password);
        if (response.isSuccess()) {
            User user = response.getData();
            if (user.getRole() == Const.Role.ROLE_ADMIN) {
                session.setAttribute(Const.CURRENT_USER, user);
                return response;
            }
        } else {
            return ServerResponse.createByErrorMessage("不是管理员。无法登录");
        }
        return response;
    }

    @RequestMapping(value = "get_user_byId", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> getUserInfoById(HttpSession session, Integer userId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }

        if (iUserService.checkAdminRole(user).isSuccess()) {
            return iUserService.getUserInfoById(userId);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping(value = "get_member_list", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<PageInfo>getMemberList(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,@RequestParam(value = "wechat",required = false) String wechat) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            return iUserService.getMemberList(pageNum, pageSize,wechat);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

}
