package com.hannamsm.shop.domain.cart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannamsm.shop.domain.cart.dao.CartDao;
import com.hannamsm.shop.domain.cart.exception.CartItemDuplicationException;
import com.hannamsm.shop.domain.cart.exception.CartItemNotFoundException;
import com.hannamsm.shop.domain.cart.vo.CartItem;
import com.hannamsm.shop.domain.cart.vo.CartItemDto;
import com.hannamsm.shop.domain.cart.vo.CartItemSearch;

@Service
public class CartService {

	@Autowired
	CartDao cartDao;

	public int findByAccountIdCount(CartItemSearch cartItemSearch) throws Exception {
		return this.cartDao.findByAccountIdCount(cartItemSearch);
	}

	public List<CartItem> findByAccountId(CartItemSearch cartItemSearch) throws Exception {
		return cartDao.findByAccountId(cartItemSearch);
	}

	public int addCartItem(CartItemDto reqCartItemDto) throws Exception {
		String accountId = reqCartItemDto.getAccountId();

		CartItem cartItem = CartItem.builder()
				.accountId(accountId)
				.itemId(reqCartItemDto.getItemId())
				.itemQty(reqCartItemDto.getItemQty())
				.regPerson(accountId)
				.lastModPerson(accountId)
				.build();
		//1. 대상 Item 가능여부
		Optional<CartItem> optionalCartItem = cartDao.findByItemId(cartItem);

		if(!optionalCartItem.isEmpty()) {
			throw new CartItemDuplicationException(cartItem.getItemId());
		}

		//2. 대상 Item이 이미 있는경우 pass
		return cartDao.insert(cartItem);
	}

	public int updateCartItem(CartItem cartItem) throws Exception {
		//상품 검색
		Optional<CartItem> optionalCartItem = cartDao.findByItemId(cartItem);
		optionalCartItem.orElseThrow(() -> new CartItemNotFoundException(cartItem.getItemId()));

		return cartDao.update(cartItem);
	}

}
