package com.hannamsm.shop.domain.cart;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.hannamsm.shop.domain.cart.vo.CartItemDto;
import com.hannamsm.shop.global.BaseControllerTest;

@Transactional
@Rollback
public class CartControllerTest extends BaseControllerTest {

	@Test
	@DisplayName("장바구니 목록 조회 테스트 - (정상)")
	public void queryCart() throws Exception {
		// Given

		// When & Then
		mockMvc.perform(get("/api/cart")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
				.param("page", "1")
				.param("listSize", "10")
			)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
			.andDo(document("cart-query"));
	}

	@Test
	@DisplayName("장바구니 요약 조회 테스트 - (정상)")
	public void queryCartSummery() throws Exception {
		// Given

		// When & Then
		mockMvc.perform(get("/api/cart/summery")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
//				.content(this.objectMapper.writeValueAsString(event))
				)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
			.andDo(document("query-cart-summery"));
	}

	@Test
	@DisplayName("장바구니 요약 조회장바구니 상품 저장 테스트 - (정상)")
	public void addCartItem() throws Exception {
		// Given
		CartItemDto cartItemDto = CartItemDto.builder()
				.itemId("DK0108977KR0101001")
				.itemQty(1)
				.build();

		// When & Then
		mockMvc.perform(post("/api/cart/{id}",cartItemDto.getItemId())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
				.content(this.objectMapper.writeValueAsString(cartItemDto))
			)
			.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
			.andDo(document("cart-save-item"));
	}

	@Test
	@DisplayName("장바구니 요약 조회장바구니 상품 저장 400 Error 테스트 - (실패)")
	public void addCartItem400Error() throws Exception {
		// Given
		CartItemDto cartItemDto = CartItemDto.builder()
				.itemId("DK0101004135KR0101001")
				.itemQty(1)
				.build();

		// When & Then
		mockMvc.perform(post("/api/cart/{id}",cartItemDto.getItemId())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
				.content(this.objectMapper.writeValueAsString(cartItemDto))
			)
			.andDo(print())
			.andExpect(status().is4xxClientError())
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
			;
	}
}
