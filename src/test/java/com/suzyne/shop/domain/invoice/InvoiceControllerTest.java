package com.suzyne.shop.domain.invoice;

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

public class InvoiceControllerTest extends BaseControllerTest {

	@Test
	@DisplayName("invoice 목록 조회 테스트 - (정상)")
	public void queryInvoices() throws Exception {
		// Given
		
		// When & Then
		mockMvc.perform(get("/api/invoice")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
//				.content(this.objectMapper.writeValueAsString(event))
				)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
			.andDo(document("query-items"));
	}
	
	@Test
	@DisplayName("invoice 상세 조회 테스트 - (정상)")
	public void queryInvoice() throws Exception {
		// Given
		
		// When & Then
		mockMvc.perform(get("/api/invoice/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
//				.content(this.objectMapper.writeValueAsString(event))
				)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
			.andDo(document("query-item"));
	}
}
