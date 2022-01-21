package com.suzyne.shop.domain.category;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.suzyne.shop.domain.category.dao.CategoryDao;
import com.suzyne.shop.domain.category.vo.CategoryDto;
import com.suzyne.shop.domain.category.vo.CategorySearch;
import com.suzyne.shop.global.BaseDaoTest;

@Disabled
public class CategoryDaoTest extends BaseDaoTest {

	@Autowired
	private CategoryDao categoryDao;

	@Test
	@DisplayName("카테고리 전체 조회 테스트")
	public void findAll() throws Exception {
		CategorySearch categorySearch = new CategorySearch(1, 10);
		categorySearch.setStoreId("st002");

		List<CategoryDto> list = this.categoryDao.findAll(categorySearch);
		System.out.println(list.toString());

		assertAll("categories",
				()->assertNotNull(list));
	}

	@Test
	@DisplayName("카테고리 전체 건수 조회 테스트")
	public void findAllCount() throws Exception {
		CategorySearch categorySearch = new CategorySearch(1, 10);
		categorySearch.setStoreId("st002");

		int count = this.categoryDao.findAllCount(categorySearch);
		System.out.println(count);

		assertAll("count",
				()->assertNotNull(count));
	}

	@Test
	@DisplayName("ID로 카테고리 조회 테스트")
	public void findByCode() throws Exception {
		CategorySearch categorySearch = CategorySearch.builder()
				.parentCategoryCd("L133")
				.parentStoreId("st002")
				.build();

		List<CategoryDto> list = this.categoryDao.findByCode(categorySearch);
		System.out.println(list.toString());

		assertAll("categories",
				()->assertNotNull(list));
	}

}
