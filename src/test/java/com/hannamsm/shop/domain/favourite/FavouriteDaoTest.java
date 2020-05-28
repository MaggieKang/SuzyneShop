package com.hannamsm.shop.domain.favourite;

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
import com.hannamsm.shop.domain.favourite.dao.FavouriteDao;
import com.hannamsm.shop.domain.favourite.vo.FavouriteItem;
import com.hannamsm.shop.domain.favourite.vo.FavouriteItemSearch;
import com.hannamsm.shop.global.BaseDaoTest;

public class FavouriteDaoTest extends BaseDaoTest {

	@Autowired
	private FavouriteDao favouriteDao;

	@Test
	@DisplayName("즐겨찾기상품 목록 조회 테스트")
	public void findByAccountId() throws Exception {
		FavouriteItemSearch favouriteItemSearch = new FavouriteItemSearch(1, 10);
		favouriteItemSearch.setAccountId("9000");

		List<FavouriteItem> list = this.favouriteDao.findByAccountId(favouriteItemSearch);
		System.out.println(list.toString());

		assertAll("list",
				()->assertNotNull(list));
	}

	@Test
	@DisplayName("즐겨찾기상품 건수 조회 테스트")
	public void findByAccountIdCount() throws Exception {
		FavouriteItemSearch favouriteItemSearch = new FavouriteItemSearch(1, 10);
		favouriteItemSearch.setAccountId("9000");

		int count = this.favouriteDao.findByAccountIdCount(favouriteItemSearch);
		System.out.println(count);

		assertAll("count",
				()->assertNotNull(count));
	}
}
