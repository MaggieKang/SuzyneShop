package com.hannamsm.shop.domain.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.domain.category.service.CategoryService;
import com.hannamsm.shop.domain.category.vo.CategoryDto;
import com.hannamsm.shop.domain.category.vo.CategorySearch;
import com.hannamsm.shop.global.vo.ResponseResutlsByPaging;

@RestController
@RequestMapping(value="/api/category", produces = MediaTypes.HAL_JSON_VALUE)
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	/*
	 * 카테고리 목록 조회
	 */
	@GetMapping
	public ResponseEntity queryCategory(@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "listSize", defaultValue = "100") int listSize) throws Exception {

		CategorySearch itemSearch = new CategorySearch(page, listSize);

		int allCount = this.categoryService.findAllCount(itemSearch);
		List<CategoryDto> list = this.categoryService.findAll(itemSearch);

		//return data
    	ResponseResutlsByPaging<CategoryDto> resResult = new ResponseResutlsByPaging<CategoryDto>(page, listSize);
		resResult.setMessage("조회되었습니다.");
		resResult.setTotalCount(allCount);
        resResult.setCurrentCount(list.size());
        resResult.setResultList(list);
        resResult.update();

        return ResponseEntity.ok(resResult);
	}

	/*
	 * 카테고리 조회
	 */
	@GetMapping("/{code}")
	public ResponseEntity queryCategoryById(@PathVariable String code) throws Exception {

		List<CategoryDto> list = this.categoryService.findByCode(code);

		//return data
    	ResponseResutlsByPaging<CategoryDto> resResult = new ResponseResutlsByPaging<CategoryDto>(1, 1000);
		resResult.setMessage("조회되었습니다.");
		resResult.setTotalCount(list.size());
        resResult.setCurrentCount(list.size());
        resResult.setResultList(list);
        resResult.update();

        return ResponseEntity.ok(resResult);
	}

}
