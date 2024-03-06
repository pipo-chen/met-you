package com.metyou.controller.backend;

import com.metyou.common.Const;
import com.metyou.common.ResponseCode;
import com.metyou.common.ServerResponse;
import com.metyou.pojo.Product;
import com.metyou.pojo.User;
import com.metyou.service.IRecordCommissionService;
import com.metyou.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/commission_record")
public class RecordCommissionManagerController {
    @Autowired
    private IRecordCommissionService iRecordCommissionService;

    @Autowired
    private IUserService iUserService;

    @RequestMapping("total_payed")
    @ResponseBody
    public ServerResponse totalPayed(HttpSession session) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            return iRecordCommissionService.totalPayed(user.getUsername());
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping("total_unpayed")
    @ResponseBody
    public ServerResponse totalUnpayed(HttpSession session) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            return iRecordCommissionService.totalUnPayed(user.getUsername());
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }
}

