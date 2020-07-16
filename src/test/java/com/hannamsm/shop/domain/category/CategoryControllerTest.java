package com.hannamsm.shop.domain.category;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.hannamsm.shop.global.BaseControllerTest;

public class CategoryControllerTest extends BaseControllerTest {

	@Test
	@DisplayName("상품 카테고리 조회 테스트 - (정상)")
	public void queryAllCategories() throws Exception {
		// Given

		// When & Then
		mockMvc.perform(get("/api/category")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
				.param("storeId", "st002")
				)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
			.andDo(document("query-categories"));
	}

	@Test
	@DisplayName("상품 카테고리 조회 테스트 - (정상)")
	public void queryCategoryByCode() throws Exception {
		// Given

		// When & Then
		mockMvc.perform(get("/api/category/")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
				.param("storeId", "st002")
				)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
			.andDo(document("query-categories"));
	}
}
