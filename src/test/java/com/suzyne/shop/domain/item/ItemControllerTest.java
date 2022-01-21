package com.suzyne.shop.domain.item;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.suzyne.shop.global.BaseControllerTest;

public class ItemControllerTest extends BaseControllerTest {

	@Test
	@DisplayName("ITEM 목록 조회 테스트 - (정상)")
	public void queryItems() throws Exception {
		// Given

		// When & Then
		mockMvc.perform(get("/api/item")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
				.param("storeId", "st002")
				.param("page", "1")
				.param("listSize", "10")
			)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("page").exists())
			.andExpect(jsonPath("listSize").exists())
			.andDo(document("query-items"));
	}

	@Test
	@DisplayName("ITEM 상세 조회 테스트 - (정상)")
	public void queryItem() throws Exception {
		// Given

		// When & Then
		mockMvc.perform(get("/api/item/{id}","DK0101004135KR0101001")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
				.param("storeId", "st002")
				)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
			.andDo(document("query-item"));
	}
}
