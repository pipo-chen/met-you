package com.metyou.service;

import com.metyou.common.ServerResponse;
import com.metyou.vo.CartVO;

public interface ICartService {
    ServerResponse<CartVO> add(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVO> update(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVO> deleteProduct(Integer userId, String productIds);

    ServerResponse<CartVO> list(Integer userId);

    ServerResponse<CartVO> selectOrUnSelect(Integer userId, Integer checked,Integer productId);

    ServerResponse<Integer> getCartProductCount(Integer userId);
}
