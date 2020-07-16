package com.hannamsm.shop.domain.store;

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

import com.hannamsm.shop.global.BaseControllerTest;

public class StoreControllerTest extends BaseControllerTest {

	@Test
	@DisplayName("Store 목록 조회 테스트 - (정상)")
	public void queryStores() throws Exception {
		// Given

		// When & Then
		mockMvc.perform(get("/api/store")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
				.param("page", "1")
				.param("listSize", "10")
			)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
			.andExpect(jsonPath("page").exists())
			.andExpect(jsonPath("listSize").exists())
			.andDo(document("query-stores"));
	}

	@Test
	@DisplayName("Store 상세 조회 테스트 - (정상)")
	public void queryStore() throws Exception {
		// Given

		// When & Then
		mockMvc.perform(get("/api/store/{id}","st001")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
				)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
			.andDo(document("query-store"));
	}
}
