package com.hannamsm.shop.domain.cart;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hannamsm.shop.domain.cart.dao.CartDao;
import com.hannamsm.shop.domain.cart.vo.CartItem;
import com.hannamsm.shop.domain.cart.vo.CartItemSearch;
import com.hannamsm.shop.global.BaseDaoTest;

public class CartDaoTest extends BaseDaoTest {

	@Autowired
	private CartDao cartDao;

	@Test
	@DisplayName("장바구니 목록 조회 테스트")
	@Disabled
	public void findByAccountId() throws Exception {
		CartItemSearch cartItemSearch = new CartItemSearch(1, 10);
		cartItemSearch.setAccountId("9000");

		List<CartItem> list = this.cartDao.findByAccountId(cartItemSearch);
		System.out.println(list.toString());

		assertAll("list",
				()->assertNotNull(list));
	}

	@Test
	@DisplayName("장바구니 목록 건수 조회 테스트")
	@Disabled
	public void findByAccountIdCount() throws Exception {
		CartItemSearch cartItemSearch = new CartItemSearch(1, 10);
		cartItemSearch.setAccountId("9000");

		int count = this.cartDao.findByAccountIdCount(cartItemSearch);
		System.out.println(count);

		assertAll("count",
				()->assertNotNull(count));
	}
}
