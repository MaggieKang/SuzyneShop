package com.hannamsm.shop.domain.favourite;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.hannamsm.shop.domain.favourite.vo.FavouriteItemDto;
import com.hannamsm.shop.global.BaseControllerTest;

@Transactional
@Rollback
public class FavouriteControllerTest extends BaseControllerTest {

	@Test
	@DisplayName("Favourite - 목록 조회 테스트 - (정상)")
	public void queryFavourite() throws Exception {
		// Given

		// When & Then
		mockMvc.perform(get("/api/favourite")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
				.param("page", "1")
				.param("listSize", "10")
			)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
			.andDo(document("favourite-query"));
	}

	@Test
	@DisplayName("Favourite - 상품 추가 테스트 - (정상)")
	public void addFavouriteItem() throws Exception {
		// Given
		FavouriteItemDto favouriteItemDto = FavouriteItemDto.builder()
				.itemId("DK0108977KR0101001")
				.build();

		// When & Then
		mockMvc.perform(post("/api/favourite/{id}",favouriteItemDto.getItemId())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
				.content(this.objectMapper.writeValueAsString(favouriteItemDto))
			)
			.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
			.andDo(document("Favourite-add-item"));
	}

	@Test
	@DisplayName("Favourite - 상품 삭제 테스트 - (정상)")
	@Disabled
	public void deleteFavouriteItem() throws Exception {
		// Given
		FavouriteItemDto favouriteItemDto = FavouriteItemDto.builder()
				.itemId("DK0108977KR0101001")
				.build();

		// When & Then
		mockMvc.perform(delete("/api/favourite/",favouriteItemDto.getItemId())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
				.content(this.objectMapper.writeValueAsString(favouriteItemDto))
			)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
			.andDo(document("Favourite-delete-item"));
	}
}
