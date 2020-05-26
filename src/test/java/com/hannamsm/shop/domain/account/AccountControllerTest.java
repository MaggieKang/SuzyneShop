package com.hannamsm.shop.domain.account;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import com.hannamsm.shop.global.BaseControllerTest;
import com.hannamsm.shop.global.properties.AppProperties;

@TestInstance(Lifecycle.PER_CLASS)
public class AccountControllerTest extends BaseControllerTest {

	@Autowired
	AppProperties appProperties;

	@Test
	public void loginTest() throws Exception {
		this.mockMvc.perform(
				post("/api/account/login")
						.with(httpBasic(appProperties.getClientId(), appProperties.getClientSecret()))
						.header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
						.param("grant_type", "password")
						.param("username", appProperties.getTestUsername())
						.param("password", appProperties.getTestPassword()))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("access_token").exists())
				.andExpect(jsonPath("token_type").exists())
				.andExpect(jsonPath("refresh_token").exists())
				.andExpect(jsonPath("expires_in").exists())
				.andExpect(jsonPath("scope").exists())
				.andDo(document("account-login",
						requestHeaders(headerWithName(HttpHeaders.AUTHORIZATION).description("Basic auth credentials")
								,headerWithName(HttpHeaders.CONTENT_TYPE).description("The \"Content-Type\" header field indicates the media type of the associated representation")),
						requestParameters(parameterWithName("grant_type").description("grant of AuthToken"),
								parameterWithName("username").description("username of AuthToken"),
								parameterWithName("password").description("password of AuthTokent")),
						responseFields(fieldWithPath("access_token").type(JsonFieldType.STRING).description("access_token of AuthToken"),
								fieldWithPath("token_type").type(JsonFieldType.STRING).description("token_type of AuthToken"),
								fieldWithPath("refresh_token").type(JsonFieldType.STRING).description("refresh_token of AuthToken"),
								fieldWithPath("expires_in").type(JsonFieldType.NUMBER).description("expires_in of AuthToken"),
								fieldWithPath("scope").type(JsonFieldType.STRING).description("scope of AuthToken"))
//						links(linkWithRel("self").description("link to self"))
						));
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
