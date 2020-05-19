package com.hannamsm.shop.domain.account;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.hannamsm.shop.domain.account.vo.AuthenticationRequest;
import com.hannamsm.shop.global.BaseControllerTest;

@TestInstance(Lifecycle.PER_CLASS)
public class AccountControllerTest extends BaseControllerTest {
	
	@Test
	public void loginTest() throws Exception {
		AuthenticationRequest authRequest = new AuthenticationRequest();
		authRequest.setUsername("9000");
		authRequest.setPassword("1234");
		
		mockMvc.perform(post("/account/login")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.content(this.objectMapper.writeValueAsString(authRequest)))
			.andDo(print())
			.andExpect(status().isOk())
//			.andExpect(header().exists(HttpHeaders.LOCATION))
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
			.andExpect(jsonPath("token").exists())
			.andExpect(jsonPath("username").value("9000"))
			.andExpect(jsonPath("authoriteis[0].authority").value("ROLE_ADMIN"))
			.andExpect(jsonPath("authoriteis[1].authority").value("ROLE_USER"))
			;
	}
	
	@Test
	@Disabled
	public void logoutTest() throws Exception {
//		AuthenticationRequest authRequest = new AuthenticationRequest();
//		authRequest.setUsername("9000");
//		authRequest.setPassword("1234");

		mockMvc.perform(post("/logout")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header("x-auth-token", "91548478D16C80B91E7B9695ED2DE0BB")
				)
		.andDo(print());
	}

}
