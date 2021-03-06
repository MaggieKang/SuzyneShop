package com.suzyne.shop.domain.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suzyne.shop.domain.category.dao.CategoryDao;
import com.suzyne.shop.domain.category.vo.CategoryDto;
import com.suzyne.shop.domain.category.vo.CategorySearch;

@Service
public class CategoryService {

	@Autowired
	CategoryDao categoryDao;

	public int findAllCount(CategorySearch categorySearch) throws Exception {
		return this.categoryDao.findAllCount(categorySearch);
	}
	public List<CategoryDto> findAll(CategorySearch categorySearch) throws Exception {
		return categoryDao.findAll(categorySearch);
	}

	public int findByCodeCount(CategorySearch categorySearch) throws Exception {
		return this.categoryDao.findByCodeCount(categorySearch);
	}
	public List<CategoryDto> findByCode(CategorySearch categorySearch) throws Exception {
		return this.categoryDao.findByCode(categorySearch);
	}
}
