package com.metyou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.metyou.common.ServerResponse;
import com.metyou.dao.ShippingMapper;
import com.metyou.pojo.Shipping;
import com.metyou.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("iShippingService")
public class ShippingServiceImpl implements IShippingService {
    @Autowired
    private ShippingMapper shippingMapper;

    @Override
    public ServerResponse add(Integer userId, Shipping shipping) {
        shipping.setUserId(userId);
        int rowCount = shippingMapper.insert(shipping);
        if (rowCount > 0) {
            Map result = Maps.newHashMap();
            result.put("shippingId", shipping.getId());
            return ServerResponse.createBySuccess("新增地址成功",result);
        }
        return ServerResponse.createByErrorMessage("新增地址失败");

    }

    @Override
    public ServerResponse<String> del(Integer userId, Integer shippingId) {
        //横向越权问题考虑 id 与 user 不对应
        int resultCount = shippingMapper.deleteByShippingIdUserId(userId, shippingId);
        if (resultCount > 0) {
            return ServerResponse.createBySuccess("删除地址成功");
        }
        return ServerResponse.createByErrorMessage("删除地址失败");
    }

    @Override
    public ServerResponse update(Integer userId, Shipping shipping) {
        shipping.setUserId(userId);
        //更新也考虑越权问题
        int rowCount = shippingMapper.updateByShipping(shipping);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("更新地址成功");
        }
        return ServerResponse.createByErrorMessage("更新地址失败");
    }

    @Override
    public ServerResponse select(Integer userId, Integer shippingId) {
        //查询也要判断越权问题
        Shipping shipping = shippingMapper.selectByShippingIdUserId(userId, shippingId);
        if (shipping == null) {
            return ServerResponse.createByErrorMessage("无法查询到该地址");
        }
        return ServerResponse.createBySuccess("查询地址成功",shipping);
    }

    @Override
    public ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Shipping> shippingList = shippingMapper.selectByUserId(userId);
        PageInfo pageInfo = new PageInfo(shippingList);
        return ServerResponse.createBySuccess(pageInfo);
    }
}
