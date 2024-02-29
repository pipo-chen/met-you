package com.metyou.controller.portal;

import com.metyou.common.Const;
import com.metyou.common.ResponseCode;
import com.metyou.common.ServerResponse;
import com.metyou.pojo.Card;
import com.metyou.pojo.User;
import com.metyou.service.ICardService;
import com.metyou.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/card/")
public class CardController {

    @Autowired
    private ICardService iCardService;

    @RequestMapping("search")
    @ResponseBody
    public ServerResponse<List<Card>> searchCrads(HttpSession session, @RequestParam(value = "status", defaultValue = "1") int status) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCardService.searchUserCards(user.getId(), status);
    }
}
