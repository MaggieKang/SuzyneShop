package com.hannamsm.shop.domain.address;

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

import com.hannamsm.shop.domain.address.vo.AccountAddress;
import com.hannamsm.shop.global.BaseControllerTest;


@Disabled
public class AddressControllerTest extends BaseControllerTest {
	
	@Test
	@DisplayName("Address 조회 테스트-(정상)")
	public void queryAddress() throws Exception {
		// Given
		
		// When & Then
		mockMvc.perform(get("/api/address")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
				)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
		.andDo(document("address-query"));
				
	}
	@Test
	@DisplayName("Address 저장테스트 -(정상)")
	public void saveAddress() throws Exception{
		//Given
		AccountAddress accountAddress = AccountAddress.builder()
				.accountNo(1)
				.address("kensalplace")
				.city("25")
				.province("3")
				.country("1")
				.postalCd("AAA BBB")
				.isDefaultAddress(true)
				.build();
		//When & Then
		mockMvc.perform(put("/api/address")
			   .contentType(MediaType.APPLICATION_JSON)
			   .accept(MediaTypes.HAL_JSON)
			   .header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
			   .content(this.objectMapper.writeValueAsString(accountAddress))
		)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
		.andDo(document("address-save-query"));
	}

}
