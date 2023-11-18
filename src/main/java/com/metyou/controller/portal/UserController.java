package com.metyou.controller.portal;

import com.metyou.common.Const;
import com.metyou.common.ServerResponse;
import com.metyou.pojo.User;
import com.metyou.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private IUserService iUserService;
    /**
     * 用户登陆
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody //自动序列化json
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        // service - mybatis - dao
        ServerResponse<User> response = iUserService.login(username, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }
}
