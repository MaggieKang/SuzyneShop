package com.hannamsm.shop.domain.category.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannamsm.shop.domain.category.dao.CategoryDao;
import com.hannamsm.shop.domain.category.vo.CategoryDto;
import com.hannamsm.shop.domain.category.vo.CategorySearch;

@Service
public class CategoryService {

	@Autowired
	CategoryDao categoryDao;

	public List<CategoryDto> findAll(CategorySearch categorySearch) throws Exception {
		return categoryDao.findAll(categorySearch);
	}

	public int findAllCount(CategorySearch categorySearch) throws Exception {
		return this.categoryDao.findAllCount(categorySearch);
	}

	public Optional<CategoryDto> findByCode(String id) throws Exception {
		return this.categoryDao.findByCode(id);
	}

}
