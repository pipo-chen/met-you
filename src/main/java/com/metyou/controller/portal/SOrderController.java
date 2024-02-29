package com.metyou.controller.portal;

import com.metyou.common.Const;
import com.metyou.common.ResponseCode;
import com.metyou.common.ServerResponse;
import com.metyou.pojo.Card;
import com.metyou.pojo.Sorder;
import com.metyou.pojo.User;
import com.metyou.service.ISOrderService;
import com.metyou.vo.SuperviseOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/supervisor/order")
public class SOrderController {
    @Autowired
    private ISOrderService isOrderService;

    @RequestMapping("search")
    @ResponseBody
    public ServerResponse<List<SuperviseOrderVO>> searchOrders(HttpSession session, @RequestParam(value = "cardId", defaultValue = "-1") int cardId, @RequestParam(value = "payway", defaultValue = "-1") int payway) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return isOrderService.searchOrderRecord(user.getId(), cardId, payway);
    }
}
