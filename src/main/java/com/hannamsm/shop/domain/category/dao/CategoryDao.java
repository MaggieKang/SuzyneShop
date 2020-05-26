package com.hannamsm.shop.domain.category.dao;

import java.util.List;

import com.hannamsm.shop.domain.category.vo.CategoryDto;
import com.hannamsm.shop.domain.category.vo.CategorySearch;

public interface CategoryDao {
	public int findAllCount(CategorySearch categorySearch) throws Exception;

	public List<CategoryDto> findAll(CategorySearch ctemSearch) throws Exception;

	public int findByCodeCount(String code) throws Exception;

	public List<CategoryDto> findByCode(String code) throws Exception;
}
