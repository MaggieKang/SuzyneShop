package com.hannamsm.shop.domain.pickup;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.hannamsm.shop.domain.pickup.vo.PickupSlotTimeSearch;
import com.hannamsm.shop.global.BaseControllerTest;

public class PickupTimeslotControllerTest extends BaseControllerTest {

	@Test
	@DisplayName("픽업 날짜 시간표 조회 테스트 - (정상)")
	public void getPickupSlogDt() throws Exception {
		// Given
		String targetDay = LocalDate.of(2020,7,15).toString();

		// When & Then
		mockMvc.perform(get("/api/pickup/slotdate")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
				.param("storeId", "st002")
				.param("slotDt", targetDay)
//				.content(this.objectMapper.writeValueAsString(input))
			)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
			.andExpect(jsonPath("page").exists())
			.andExpect(jsonPath("listSize").exists())
			.andDo(document("query-pickupSlogDt"));
	}

	@Test
	@DisplayName("픽업 시간 조회 테스트 - (정상)")
	public void queryStore() throws Exception {
		// Given
		String targetDay = LocalDate.of(2020,7,15).toString();
		String targetTime = LocalTime.of(11,0,0,0).toString();

		PickupSlotTimeSearch pickupTimeslot = PickupSlotTimeSearch.builder()
				.storeId("st002")
				.slotDt(targetDay)
				.slotTime(targetTime)
				.build();

		// When & Then
		mockMvc.perform(get("/api/pickup/slottime")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
				.content(this.objectMapper.writeValueAsString(pickupTimeslot))
			)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
			.andDo(document("query-pickupSlottime"));
	}
}
