package com.hannamsm.shop.domain.profile;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.hannamsm.shop.global.BaseControllerTest;
import com.hannamsm.shop.domain.profile.vo.Customer;

@Disabled
public class ProfileControllerTest extends BaseControllerTest {

	@Test
	@DisplayName("Profile 조회 테스트 - (정상)")
	public void queryProfile() throws Exception {
		// Given

		// When & Then
		mockMvc.perform(get("/api/profile")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
				)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
		.andDo(document("profile-query"));
	}

	@Test
	@DisplayName("Profile 저장테스트 -(정상)")
	public void saveProfile() throws Exception{
		// Given
		Customer customer = Customer.builder()
					.accountNo(1)
					.firstName("ss")
					.lastName("baek")
					.customerEmail("1234@gmail.com")
					.customerPhoneNumber("123-456-7897")
					.extensionNumber(null)
					.build();
		// When & Then
		mockMvc.perform(put("/api/profile/save")
			   .contentType(MediaType.APPLICATION_JSON)
			   .accept(MediaTypes.HAL_JSON)
			   .header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
			   .content(this.objectMapper.writeValueAsString(customer))
		)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
		.andDo(document("profile-save-query"));
	}

}
