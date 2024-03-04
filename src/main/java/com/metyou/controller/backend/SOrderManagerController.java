package com.metyou.controller.backend;

import com.metyou.common.Const;
import com.metyou.common.ResponseCode;
import com.metyou.common.ServerResponse;
import com.metyou.pojo.CardRecord;
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
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/manage/sorder")
public class SOrderManagerController {

    @Autowired
    private ISOrderService isOrderService;

    @Autowired
    private IUserService iUserService;

    @RequestMapping("consume")
    @ResponseBody
    public ServerResponse<String> consume(HttpSession session, Integer userId, Integer commodityId, Integer commodityNum, String supervisName, BigDecimal salePrice, Integer payway, Integer cardId, String note) {
        Sorder sorder = new Sorder(userId, commodityId, commodityNum, supervisName, salePrice, payway, cardId, note);
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            CardRecord record = new CardRecord();
            record.setCreator(user.getUsername());
            record.setCreatorId(user.getId());
            record.setMoney(salePrice);
            record.setCardId(cardId);
            record.setOperator("sub");
            return isOrderService.consume(sorder, record);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    /**
     * 查询该监督员当前的学员订单，所有订单含提成&状态&开始结束时间（）
     * @param session
     * @return
     */
    @RequestMapping("search")
    @ResponseBody
    public ServerResponse<List<SuperviseOrderVO>> searchOrders(HttpSession session) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登陆，请登录");
        }
        //校验一下是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //增加处理分类的逻辑
            return isOrderService.search(user.getUsername());

        } else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }
}
