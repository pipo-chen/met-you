package com.metyou.service.impl;

import com.metyou.common.ServerResponse;
import com.metyou.dao.CardMapper;
import com.metyou.dao.CardRecordMapper;
import com.metyou.pojo.Card;
import com.metyou.pojo.CardRecord;
import com.metyou.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service("iCardService")
public class CardServiceImpl implements ICardService {

    @Autowired
    private CardMapper cardMapper;

    @Autowired
    private CardRecordMapper cardRecordMapper;

    @Override
    public ServerResponse<List<Card>> searchUserCards(Integer userId, Integer status) {
        List<Card>cardlist = cardMapper.searchCard(userId, status);
        if (cardlist.isEmpty()) {
            return ServerResponse.createByErrorMessage("用户没有会员卡");
        }
        return ServerResponse.createBySuccess(cardlist);
    }

    @Override
    public ServerResponse<List<Card>> searchUserCardsByIdOrWechat(Integer userId, Integer status, String wechat) {
        List<Card>cardlist = cardMapper.searchCardByIdOrWechat(userId, status, wechat);
        if (cardlist.isEmpty()) {
            return ServerResponse.createByErrorMessage("用户没有会员卡");
        }
        return ServerResponse.createBySuccess(cardlist);
    }

    @Override
    public ServerResponse recharge(CardRecord cardRecord) {

        //1.把cardrecord新增记录
        int rowCount = cardRecordMapper.insertSelective(cardRecord);

        if (rowCount > 0) {
            //2.把card表中的余额加上balance
            Card card = cardMapper.selectByPrimaryKey(cardRecord.getCardId());
            BigDecimal balance = card.getBalance().add(cardRecord.getMoney());
            //把钱更新掉
            card.setBalance(balance);
            int updateCount = cardMapper.updateBalance(card);
            if (updateCount > 0) {
                return ServerResponse.createBySuccess("充值成功,当前余额："+card.getBalance());
            }
        }
        return ServerResponse.createByErrorMessage("充值失败");
    }
}
