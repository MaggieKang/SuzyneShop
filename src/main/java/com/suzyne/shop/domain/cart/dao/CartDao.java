package com.suzyne.shop.domain.cart.dao;

import java.util.List;
import java.util.Optional;

import com.suzyne.shop.domain.cart.vo.CartItem;
import com.suzyne.shop.domain.cart.vo.CartItemDto;
import com.suzyne.shop.domain.cart.vo.CartItemSearch;
import com.suzyne.shop.domain.cart.vo.CartSummary;
import com.suzyne.shop.global.mapper.HnsShopConnMapper;

@HnsShopConnMapper
public interface CartDao {
	public int findByAccountIdCount(CartItemSearch cartItemSearch) throws Exception;

	public List<CartItemDto> findByAccountId(CartItemSearch cartItemSearch) throws Exception;

	public CartSummary findSummaryByAccountId(CartItemSearch cartItemSearch) throws Exception;

	public Optional<CartItem> findByItemId(CartItem cartItem) throws Exception;

	public int add(CartItem cartItem) throws Exception;

	public int down(CartItem cartItem) throws Exception;

	public int save(CartItem cartItem) throws Exception;

	public int delete(CartItem cartItem) throws Exception;

	public int deleteAllForOrder(CartItemSearch cartItemSearch) throws Exception;
}
