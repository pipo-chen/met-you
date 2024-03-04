package com.metyou.service.impl;

import com.metyou.common.ServerResponse;
import com.metyou.dao.CardMapper;
import com.metyou.dao.CardRecordMapper;
import com.metyou.dao.UserMapper;
import com.metyou.pojo.Card;
import com.metyou.pojo.CardRecord;
import com.metyou.pojo.User;
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

    @Autowired
    private UserMapper userMapper;

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

    @Override
    public ServerResponse createCard(Card card) {
        User user = userMapper.selectByPrimaryKey(card.getUserId());
        //根据用户id获取以下信息
        card.setUsername(user.getUsername());
        card.setWechat(user.getWechat());
        switch (card.getLevel()) {
            //青铜卡赠金
            case 1:
                card.setService("青铜无折扣，返赠金");
                break;
            case 2:
                card.setService("白银卡9折");
                break;
            case 3:
                card.setService("钻石卡88折");
                break;
            case 4:
                card.setService("紫金卡85折");
                break;
            default:
                return ServerResponse.createByErrorMessage("卡等级错误");
        }

        int rowCount = cardMapper.insertSelective(card);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("创建成功");
        }
        return ServerResponse.createByErrorMessage("创建失败");
    }
}
