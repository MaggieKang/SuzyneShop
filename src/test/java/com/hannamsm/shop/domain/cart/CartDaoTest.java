package com.hannamsm.shop.domain.cart;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.hannamsm.shop.domain.cart.dao.CartDao;
import com.hannamsm.shop.domain.cart.vo.CartItem;
import com.hannamsm.shop.domain.cart.vo.CartItemSearch;
import com.hannamsm.shop.global.BaseDaoTest;

@Transactional
@Rollback
public class CartDaoTest extends BaseDaoTest {

	@Autowired
	private CartDao cartDao;

	@Test
	@DisplayName("Cart 목록 조회 테스트")
	public void findByAccountId() throws Exception {
		CartItemSearch cartItemSearch = new CartItemSearch(1, 10);
		cartItemSearch.setAccountId("9000");
		cartItemSearch.setStoreId("st002");

		List<CartItem> list = this.cartDao.findByAccountId(cartItemSearch);
		System.out.println(list.toString());

		assertAll("list",
				()->assertNotNull(list));
	}

	@Test
	@DisplayName("Cart 목록 건수 조회 테스트")
	public void findByAccountIdCount() throws Exception {
		CartItemSearch cartItemSearch = new CartItemSearch(1, 10);
		cartItemSearch.setAccountId("9000");
		cartItemSearch.setStoreId("st002");

		int count = this.cartDao.findByAccountIdCount(cartItemSearch);
		System.out.println(count);

		assertAll("count",
				()->assertNotNull(count));
	}

	@Test
	@DisplayName("Cart 추가 테스트")
	public void addCartItem() throws Exception {
		CartItem cartItem = CartItem.builder()
				.accountId("9000")
				.storeId("st002")
				.itemId("DK0108977KR0101001")
				.itemQty(1)
				.regPerson("test")
				.lastModPerson("test")
				.build();

		int count = this.cartDao.add(cartItem);
		System.out.println(count);

		assertAll("saveCount",
				()->assertNotNull(count));
	}

	@Test
	@DisplayName("Cart 저장 테스트")
	public void saveCartItem() throws Exception {
		CartItem cartItem = CartItem.builder()
				.accountId("9000")
				.storeId("st002")
				.itemId("DK0108977KR0101001")
				.itemQty(1)
				.regPerson("test")
				.lastModPerson("test")
				.build();

		int count = this.cartDao.save(cartItem);
		System.out.println(count);

		assertAll("saveCount",
				()->assertNotNull(count));
	}

	@Test
	@DisplayName("Cart 삭제 테스트")
	public void deleteCartItem() throws Exception {
		CartItem cartItem = CartItem.builder()
				.accountId("9000")
				.storeId("st002")
				.itemId("123123123")
				.build();

		int count = this.cartDao.delete(cartItem);
		System.out.println(count);

		assertAll("deleteCount",
				()->assertNotNull(count));
	}
}
