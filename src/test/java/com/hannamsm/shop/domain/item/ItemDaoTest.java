package com.hannamsm.shop.domain.item;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hannamsm.shop.domain.item.dao.ItemDao;
import com.hannamsm.shop.domain.item.vo.Item;
import com.hannamsm.shop.domain.item.vo.ItemDto;
import com.hannamsm.shop.domain.item.vo.ItemSearch;
import com.hannamsm.shop.global.BaseDaoTest;

@Disabled
public class ItemDaoTest extends BaseDaoTest {

	@Autowired
	private ItemDao itemDao;

	@Test
	@DisplayName("상품 전체 조회 테스트")
	public void findAll() throws Exception {
		ItemSearch itemSearch = new ItemSearch(1, 10);
		itemSearch.setStoreId("st002");
		itemSearch.setItemSort("O0");

		List<Item> items = this.itemDao.findAll(itemSearch);
		System.out.println(items.toString());

		assertAll("items",
				()->assertNotNull(items));
	}

	@Test
	@DisplayName("상품 전체 건수 조회 테스트")
	public void findAllCount() throws Exception {
		ItemSearch itemSearch = new ItemSearch(1, 10);
		itemSearch.setStoreId("st002");

		int count = this.itemDao.findAllCount(itemSearch);
		System.out.println(count);

		assertAll("count",
				()->assertNotNull(count));
	}

	@Test
	@DisplayName("ID로 상품 조회 테스트")
	public void findById() throws Exception {
		ItemSearch itemSearch = new ItemSearch(1, 10);
		itemSearch.setItemId("ABC123123");
		itemSearch.setStoreId("st002");

		Optional<ItemDto> item = this.itemDao.findById(itemSearch);
		System.out.println(item.toString());

		assertAll("items",
				()->assertNotNull(item));
	}

	@Test
	@DisplayName("Name으로 상품 조회 테스트")
	public void findByName() throws Exception {
		ItemSearch itemSearch = new ItemSearch(1, 10);
		itemSearch.setItemName("asdasd");
		itemSearch.setStoreId("st002");

		List<Item> items = this.itemDao.findByName(itemSearch);
		System.out.println(items.toString());

		assertAll("items",
				()->assertNotNull(items));
	}
}
