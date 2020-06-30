package com.hannamsm.shop.domain.cart.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannamsm.shop.domain.cart.dao.CartDao;
import com.hannamsm.shop.domain.cart.exception.CartItemNotFoundException;
import com.hannamsm.shop.domain.cart.vo.CartItem;
import com.hannamsm.shop.domain.cart.vo.CartItemDto;
import com.hannamsm.shop.domain.cart.vo.CartItemSearch;
import com.hannamsm.shop.domain.cart.vo.CartSummery;
import com.hannamsm.shop.domain.item.dao.ItemDao;
import com.hannamsm.shop.domain.item.vo.Item;
import com.hannamsm.shop.domain.item.vo.ItemSearch;

@Service
public class CartService {

	@Autowired
	CartDao cartDao;

	@Autowired
	ItemDao itemDao;

	public int findByAccountIdCount(CartItemSearch cartItemSearch) throws Exception {
		return this.cartDao.findByAccountIdCount(cartItemSearch);
	}

	public List<CartItem> findByAccountId(CartItemSearch cartItemSearch) throws Exception {
		return cartDao.findByAccountId(cartItemSearch);
	}

	public CartSummery findSummeryByAccountId(CartItemSearch cartItemSearch) throws Exception {
		return cartDao.findSummeryByAccountId(cartItemSearch);
	}

	public int addCartItem(CartItemDto cartItemDto) throws Exception {
		String accountId = cartItemDto.getAccountId();

		CartItem cartItem = CartItem.builder()
				.accountId(accountId)
				.storeId(cartItemDto.getStoreId())
				.itemId(cartItemDto.getItemId())
				.itemQty(cartItemDto.getItemQty())
				.regPerson(accountId)
				.lastModPerson(accountId)
				.build();

		ItemSearch itemSearch = ItemSearch.builder()
				.storeId(cartItemDto.getStoreId())
				.itemId(cartItem.getItemId())
				.build();

		//상품 검색
		Optional<Item> optionalItem = this.itemDao.findById(itemSearch);
		optionalItem.orElseThrow(() -> new CartItemNotFoundException(itemSearch.getItemId()));

		return cartDao.add(cartItem);
	}

	public int saveCartItem(CartItemDto cartItemDto) throws Exception {
		String accountId = cartItemDto.getAccountId();

		CartItem cartItem = CartItem.builder()
				.accountId(accountId)
				.storeId(cartItemDto.getStoreId())
				.itemId(cartItemDto.getItemId())
				.itemQty(cartItemDto.getItemQty())
				.regPerson(accountId)
				.lastModPerson(accountId)
				.build();

		//Cart 상품 검색
		Optional<CartItem> optionalCartItem = cartDao.findByItemId(cartItem);
		optionalCartItem.orElseThrow(() -> new CartItemNotFoundException(cartItem.getItemId()));

		return cartDao.save(cartItem);
	}

	public int deleteCartItem(@Valid CartItemDto cartItemDto) throws Exception {
		String accountId = cartItemDto.getAccountId();

		CartItem cartItem = CartItem.builder()
				.accountId(accountId)
				.storeId(cartItemDto.getStoreId())
				.itemId(cartItemDto.getItemId())
				.itemQty(cartItemDto.getItemQty())
				.regPerson(accountId)
				.lastModPerson(accountId)
				.build();

		return cartDao.delete(cartItem);
	}

}
