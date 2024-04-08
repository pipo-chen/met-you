package com.metyou.controller.backend;

import com.metyou.common.Const;
import com.metyou.common.ResponseCode;
import com.metyou.common.ServerResponse;
import com.metyou.pojo.Card;
import com.metyou.pojo.CardRecord;
import com.metyou.pojo.User;
import com.metyou.service.ICardService;
import com.metyou.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.UUID;

@Controller
@RequestMapping("/manage/card")
public class CardManagerController {
    @Autowired
    private ICardService iCardService;

    @Autowired
    private IUserService iUserService;

    @RequestMapping("search")
    @ResponseBody
    public ServerResponse searchUserCards(HttpSession session, Integer userId,  @RequestParam(value = "status", defaultValue = "1") Integer status,@RequestParam(value = "wechat", defaultValue = "")  String wechat) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //查询指定用户会员卡信息
            return iCardService.searchUserCardsByIdOrWechat(userId, status, wechat);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping("recharge")
    @ResponseBody
    public ServerResponse addCardRecord(HttpSession session, Integer cardId, BigDecimal money, @RequestParam(value = "note", defaultValue = "充值") String note) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            CardRecord cardRecord = new CardRecord();
            cardRecord.setOperator("add");
            cardRecord.setNote(note);
            cardRecord.setCreatorId(user.getId());
            cardRecord.setCreator(user.getUsername());
            cardRecord.setMoney(money);
            cardRecord.setCardId(cardId);

            return iCardService.recharge(cardRecord);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping("create_card")
    @ResponseBody
    public ServerResponse createCard(HttpSession session, Integer userId, Integer card_level, BigDecimal money) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            
            return ServerResponse.createByErrorCodeMessage(
                    ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            Card card = new Card();
            card.setBalance(money);
            card.setLevel(card_level);
            card.setStatus(1);
            card.setUserId(userId);
            //ID随机生成
            return iCardService.createCard(card);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }
}
