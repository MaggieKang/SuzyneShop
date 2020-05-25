package com.hannamsm.shop.domain.category;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hannamsm.shop.domain.category.dao.CategoryDao;
import com.hannamsm.shop.domain.category.vo.CategoryDto;
import com.hannamsm.shop.domain.category.vo.CategorySearch;
import com.hannamsm.shop.global.BaseDaoTest;

public class CategoryDaoTest extends BaseDaoTest {

	@Autowired
	private CategoryDao categoryDao;

	@Test
	@DisplayName("카테고리 전체 조회 테스트")
	public void findAll() throws Exception {
		CategorySearch categorySearch = new CategorySearch(1, 10);

		List<CategoryDto> items = this.categoryDao.findAll(categorySearch);
		System.out.println(items.toString());

		assertAll("items",
				()->assertNotNull(items));
	}

	@Test
	@DisplayName("카테고리 전체 건수 조회 테스트")
	public void findAllCount() throws Exception {
		CategorySearch categorySearch = new CategorySearch(1, 10);

		int count = this.categoryDao.findAllCount(categorySearch);
		System.out.println(count);

		assertAll("count",
				()->assertNotNull(count));
	}

	@Test
	@DisplayName("ID로 카테고리 조회 테스트")
	public void findByCode() throws Exception {
		Optional<CategoryDto> item = this.categoryDao.findByCode("N111");
		System.out.println(item.toString());

		assertAll("category",
				()->assertNotNull(item));
	}

}
