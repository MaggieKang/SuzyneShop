package com.hannamsm.shop.domain.cart.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannamsm.shop.domain.cart.dao.CartDao;
import com.hannamsm.shop.domain.cart.exception.CartItemNotFoundException;
import com.hannamsm.shop.domain.cart.vo.CartDeleteItemDto;
import com.hannamsm.shop.domain.cart.vo.CartItem;
import com.hannamsm.shop.domain.cart.vo.CartItemDto;
import com.hannamsm.shop.domain.cart.vo.CartItemSearch;
import com.hannamsm.shop.domain.cart.vo.CartSummary;
import com.hannamsm.shop.domain.item.dao.ItemDao;
import com.hannamsm.shop.domain.item.vo.ItemForAddCart;
import com.hannamsm.shop.domain.item.vo.ItemForAddCartSearch;

@Service
public class CartService {

	@Autowired
	CartDao cartDao;

	@Autowired
	ItemDao itemDao;

	public int findByAccountIdCount(CartItemSearch cartItemSearch) throws Exception {
		return this.cartDao.findByAccountIdCount(cartItemSearch);
	}

	public List<CartItemDto> findByAccountId(CartItemSearch cartItemSearch) throws Exception {
		return cartDao.findByAccountId(cartItemSearch);
	}

	public CartSummary findSummaryByAccountId(CartItemSearch cartItemSearch) throws Exception {
		return cartDao.findSummaryByAccountId(cartItemSearch);
	}

	public int addCartItem(CartItemDto cartItemDto) throws Exception {
		int accountNo = cartItemDto.getAccountNo();

		ItemForAddCartSearch itemForAddCartSearch = ItemForAddCartSearch.builder()
				.accountNo(accountNo)
				.storeId(cartItemDto.getStoreId())
				.itemId(cartItemDto.getItemId())
				.build();

		//상품 검색
		Optional<ItemForAddCart> optionalItem = this.itemDao.findForAddCart(itemForAddCartSearch);
		optionalItem.orElseThrow(() -> new CartItemNotFoundException(itemForAddCartSearch.getItemId()));

		//카트 동일 상품 검색후 R:일반상품, P:프로모션, M:멤버쉽 상품판매종류를 검색한다.
		ItemForAddCart itemForAddCart = optionalItem.get();

		CartItem cartItem = CartItem.builder()
				.accountNo(accountNo)
				.storeId(cartItemDto.getStoreId())
				.itemId(cartItemDto.getItemId())
				.itemSalesTypeCd(itemForAddCart.getItemSalesTypeCd())
				.itemQty(itemForAddCart.getAddSalesQty())
				.regPerson(String.valueOf(accountNo))
				.lastModPerson(String.valueOf(accountNo))
				.build();

		return cartDao.add(cartItem);
	}

	public int saveCartItem(CartItemDto cartItemDto) throws Exception {
		int accountNo = cartItemDto.getAccountNo();

		CartItem cartItem = CartItem.builder()
				.accountNo(accountNo)
				.storeId(cartItemDto.getStoreId())
				.itemId(cartItemDto.getItemId())
				.itemSalesTypeCd(cartItemDto.getItemSalesTypeCd())
				.itemQty(cartItemDto.getItemQty())
				.regPerson(String.valueOf(accountNo))
				.lastModPerson(String.valueOf(accountNo))
				.build();

		//Cart 상품 검색
		Optional<CartItem> optionalCartItem = cartDao.findByItemId(cartItem);
		optionalCartItem.orElseThrow(() -> new CartItemNotFoundException(cartItem.getItemId()));

		return cartDao.save(cartItem);
	}

	public int deleteCartItem(@Valid CartDeleteItemDto cartDeleteItemDto) throws Exception {
		int accountNo = cartDeleteItemDto.getAccountNo();

		CartItem cartItem = CartItem.builder()
				.accountNo(accountNo)
				.storeId(cartDeleteItemDto.getStoreId())
				.itemId(cartDeleteItemDto.getItemId())
				.itemSalesTypeCd(cartDeleteItemDto.getItemSalesTypeCd())
				.regPerson(String.valueOf(accountNo))
				.lastModPerson(String.valueOf(accountNo))
				.build();

		return cartDao.delete(cartItem);
	}
}
