package com.hannamsm.shop.domain.favourite;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hannamsm.shop.domain.favourite.dao.FavouriteDao;
import com.hannamsm.shop.domain.favourite.vo.FavouriteItem;
import com.hannamsm.shop.domain.favourite.vo.FavouriteItemSearch;
import com.hannamsm.shop.global.BaseDaoTest;

public class FavouriteDaoTest extends BaseDaoTest {

	@Autowired
	private FavouriteDao favouriteDao;

	@Test
	@DisplayName("즐겨찾기상품 목록 조회 테스트")
	@Disabled
	public void findByAccountId() throws Exception {
		FavouriteItemSearch favouriteItemSearch = new FavouriteItemSearch(1, 10);
		favouriteItemSearch.setAccountNo(1);

		List<FavouriteItem> list = this.favouriteDao.findByAccountId(favouriteItemSearch);
		System.out.println(list.toString());

		assertAll("list",
				()->assertNotNull(list));
	}

	@Test
	@DisplayName("즐겨찾기상품 건수 조회 테스트")
	@Disabled
	public void findByAccountIdCount() throws Exception {
		FavouriteItemSearch favouriteItemSearch = new FavouriteItemSearch(1, 10);
		favouriteItemSearch.setAccountNo(1);

		int count = this.favouriteDao.findByAccountIdCount(favouriteItemSearch);
		System.out.println(count);

		assertAll("count",
				()->assertNotNull(count));
	}

	@Test
	@DisplayName("Favourite 추가 테스트")
	@Disabled
	public void addFavouriteItem() throws Exception {
		FavouriteItem favouriteItem = FavouriteItem.builder()
				.accountNo(1)
				.itemId("DK0108977KR0101001")
				.regPerson("test")
				.lastModPerson("test")
				.build();

		int count = this.favouriteDao.add(favouriteItem);
		System.out.println(count);

		assertAll("saveCount",
				()->assertNotNull(count));
	}

	@Test
	@DisplayName("Favourite 삭제 테스트")
	@Disabled
	public void deleteFavouriteItem() throws Exception {
		FavouriteItem favouriteItem = FavouriteItem.builder()
				.accountNo(1)
				.itemId("DK0108977KR0101001")
				.build();

		int count = this.favouriteDao.delete(favouriteItem);
		System.out.println(count);

		assertAll("deleteCount",
				()->assertNotNull(count));
	}
}
