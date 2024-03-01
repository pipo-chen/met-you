package com.metyou.controller.backend;

import com.metyou.common.Const;
import com.metyou.common.ResponseCode;
import com.metyou.common.ServerResponse;
import com.metyou.pojo.Sorder;
import com.metyou.pojo.User;
import com.metyou.service.ISOrderService;
import com.metyou.service.IUserService;
import com.metyou.vo.SuperviseOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/manage/sorder")
public class SOrderManagerController {

    @Autowired
    private ISOrderService isOrderService;

    @Autowired
    private IUserService iUserService;

    @RequestMapping("recharge")
    @ResponseBody
    public ServerResponse recharge(HttpSession session, Sorder sorder) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            return isOrderService.recharge(sorder);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }
}
