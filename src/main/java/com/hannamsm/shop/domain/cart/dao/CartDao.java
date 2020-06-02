package com.hannamsm.shop.domain.cart.dao;

import java.util.List;
import java.util.Optional;

import com.hannamsm.shop.domain.cart.vo.CartItem;
import com.hannamsm.shop.domain.cart.vo.CartItemSearch;

public interface CartDao {
	public int findByAccountIdCount(CartItemSearch cartItemSearch) throws Exception;

	public List<CartItem> findByAccountId(CartItemSearch cartItemSearch) throws Exception;

	public Optional<CartItem> findByItemId(CartItem cartItem) throws Exception;

	public int add(CartItem cartItem) throws Exception;

	public int save(CartItem cartItem) throws Exception;

	public int delete(CartItem cartItem) throws Exception;
}
