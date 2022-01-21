package com.suzyne.shop.domain.order;

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

import com.suzyne.shop.global.BaseControllerTest;

public class OrderControllerTest extends BaseControllerTest {

	@Test
	@DisplayName("Order 목록 조회 테스트 - (정상)")
	public void queryOrders() throws Exception {
		// Given

		// When & Then
		mockMvc.perform(get("/api/order")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
				.param("page", "1")
				.param("listSize", "10")
			)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
			.andDo(document("order-list-query"));
	}

	@Test
	@DisplayName("Order 상세 조회 테스트 - (정상)")
	public void queryOrder() throws Exception {
		// Given

		// When & Then
		mockMvc.perform(get("/api/order/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
				.param("page", "1")
				.param("listSize", "10")
			)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
			.andDo(document("order-detail-query"));
	}
}
