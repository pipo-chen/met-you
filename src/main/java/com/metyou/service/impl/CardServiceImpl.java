package com.metyou.service.impl;

import com.metyou.common.ServerResponse;
import com.metyou.dao.CardMapper;
import com.metyou.pojo.Card;
import com.metyou.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("iCardService")
public class CardServiceImpl implements ICardService {

    @Autowired
    private CardMapper cardMapper;

    @Override
    public ServerResponse<List<Card>> searchUserCards(Integer userId, Integer status) {
        List<Card>cardlist = cardMapper.searchCard(userId, status);
        if (cardlist.isEmpty()) {
            return ServerResponse.createByErrorMessage("用户没有会员卡");
        }
        return ServerResponse.createBySuccess(cardlist);
    }
}
