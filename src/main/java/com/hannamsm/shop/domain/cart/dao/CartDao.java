package com.hannamsm.shop.domain.cart.dao;

import java.util.List;

import com.hannamsm.shop.domain.cart.vo.CartItem;
import com.hannamsm.shop.domain.cart.vo.CartItemSearch;

public interface CartDao {
	public int findByAccountIdCount(CartItemSearch cartItemSearch) throws Exception;

	public List<CartItem> findByAccountId(CartItemSearch cartItemSearch) throws Exception;
}
