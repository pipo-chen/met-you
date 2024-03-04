package com.metyou.service;

import com.metyou.common.ServerResponse;
import com.metyou.pojo.Card;
import com.metyou.pojo.CardRecord;

import java.math.BigDecimal;
import java.util.List;

public interface ICardService {

    ServerResponse<List<Card>> searchUserCards(Integer userId, Integer status);

    ServerResponse<List<Card>> searchUserCardsByIdOrWechat(Integer userId, Integer status, String wechat);

    ServerResponse recharge(CardRecord cardRecord);

    ServerResponse createCard(Card card);
}
