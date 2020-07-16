package com.hannamsm.shop.domain.store;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hannamsm.shop.domain.store.dao.StoreDao;
import com.hannamsm.shop.domain.store.vo.Store;
import com.hannamsm.shop.domain.store.vo.StoreSearch;
import com.hannamsm.shop.global.BaseDaoTest;

public class StoreDaoTest extends BaseDaoTest {

	@Autowired
	private StoreDao storeDao;

	@Test
	@DisplayName("매장 전체 조회 테스트")
	@Disabled
	public void findAll() throws Exception {
		StoreSearch storeSearch = new StoreSearch(1, 10);

		List<Store> stores = this.storeDao.findAll(storeSearch);
		System.out.println(stores.toString());

		assertAll("stores",
				()->assertNotNull(stores));
	}

	@Test
	@DisplayName("매장 전체 건수 조회 테스트")
	@Disabled
	public void findAllCount() throws Exception {
		StoreSearch storeSearch = new StoreSearch(1, 10);

		int count = this.storeDao.findAllCount(storeSearch);
		System.out.println(count);

		assertAll("count",
				()->assertNotNull(count));
	}

	@Test
	@DisplayName("ID로 매장 조회 테스트")
	@Disabled
	public void findById() throws Exception {
		Optional<Store> optionalStore = this.storeDao.findById("st001");
		System.out.println(optionalStore.get().toString());

		assertAll("store",
				()->assertNotNull(optionalStore.get()));
	}

}
