package com.metyou.service;

import com.metyou.common.ServerResponse;
import com.metyou.pojo.Card;

import java.util.List;

public interface ICardService {

    ServerResponse<List<Card>> searchUserCards(Integer userId, Integer status);

}
