package com.metyou.controller.backend;

import com.metyou.common.Const;
import com.metyou.common.ResponseCode;
import com.metyou.common.ServerResponse;
import com.metyou.pojo.CardRecord;
import com.metyou.pojo.Sorder;
import com.metyou.pojo.User;
import com.metyou.service.ISuperviceOrderService;
import com.metyou.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Controller
@RequestMapping("/manage/sorder_v2")
public class SuperviseOrderManagerController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ISuperviceOrderService iSuperviceOrderService;

    @RequestMapping("init_staff_order")
    @ResponseBody
    public ServerResponse initStaffOrder(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);

        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        if (iUserService.checkSuperAdminRole(user).isSuccess()) {
            return iSuperviceOrderService.initStaffOrder(user.getUsername(), user.getId());

        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping("get_all_orders")
    @ResponseBody
    public ServerResponse getAllOrders(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        if (iUserService.checkSuperAdminRole(user).isSuccess()) {
            return iSuperviceOrderService.getAllOrders(pageNum, pageSize);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

}
