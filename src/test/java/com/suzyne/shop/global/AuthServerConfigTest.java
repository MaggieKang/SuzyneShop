package com.suzyne.shop.global;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.restdocs.payload.JsonFieldType;

import com.suzyne.shop.global.properties.AppProperties;

public class AuthServerConfigTest extends BaseControllerTest {

	@Autowired
	AppProperties appProperties;

	@Test
	@DisplayName("인증 토큰을  발급 받는 테스트")
	public void getAuthTokenByPassword() throws Exception {

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
				.andDo(document("post-tokenAccess",
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
	@DisplayName("재발급토큰을 사용하여 인증 토큰을 발급 받는 테스트")
	public void getAuthTokenByRefreshToken() throws Exception {
		this.mockMvc.perform(
				post("/api/account/login")
						.with(httpBasic(appProperties.getClientId(), appProperties.getClientSecret()))
						.header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
						.param("grant_type", "refresh_token")
						.param("refresh_token", super.getRefreshToken()))
				.andDo(print())
				.andExpect(status().isOk())
//				.andExpect(jsonPath("access_token").exists())
//				.andExpect(jsonPath("token_type").exists())
//				.andExpect(jsonPath("refresh_token").exists())
//				.andExpect(jsonPath("expires_in").exists())
//				.andExpect(jsonPath("scope").exists())
				.andDo(document("post-tokenRefresh"));
//		,
//						requestHeaders(headerWithName(HttpHeaders.AUTHORIZATION).description("Basic auth credentials"),
//								headerWithName(HttpHeaders.CONTENT_TYPE).description("The \"Content-Type\" header field indicates the media type of the associated representation")),
//						requestParameters(parameterWithName("grant_type").description("grant type of AuthToken"),
//								parameterWithName("refresh_token").description("refresh_token of AuthToken")),
//						responseFields(fieldWithPath("access_token").type(JsonFieldType.STRING).description("access_token of AuthToken"),
//								fieldWithPath("token_type").type(JsonFieldType.STRING).description("token_type of AuthToken"),
//								fieldWithPath("refresh_token").type(JsonFieldType.STRING).description("refresh_token of AuthToken"),
//								fieldWithPath("expires_in").type(JsonFieldType.NUMBER).description("expires_in of AuthToken"),
//								fieldWithPath("scope").type(JsonFieldType.STRING).description("scope of AuthToken"))
////						links(linkWithRel("self").description("link to self"))
//						));
	}

	@Test
	@DisplayName("Access토큰을 사용하여 토큰 확인 및 토큰 정보를 받는 테스트")
	public void getAuthTokenCheckByAccessToken() throws Exception {

		this.mockMvc.perform(
				get("/api/account/token-check")
						.with(httpBasic(appProperties.getClientId(), appProperties.getClientSecret()))
						.header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
						.param("token", super.getAccessToken()))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("active").exists())
				.andExpect(jsonPath("exp").exists())
				.andExpect(jsonPath("user_name").exists())
				.andExpect(jsonPath("authorities").exists())
				.andExpect(jsonPath("client_id").exists())
				.andExpect(jsonPath("scope").exists())
				.andDo(document("get-tokenCheck",
						requestHeaders(headerWithName(HttpHeaders.AUTHORIZATION).description("Basic auth credentials"),
								headerWithName(HttpHeaders.CONTENT_TYPE).description("The \"Content-Type\" header field indicates the media type of the associated representation")),
						requestParameters(parameterWithName("token").description("access_token of AuthToken")),
						responseFields(fieldWithPath("active").type(JsonFieldType.BOOLEAN).description("active of AuthToken"),
								fieldWithPath("exp").type(JsonFieldType.NUMBER).description("exp of AuthToken"),
								fieldWithPath("user_name").type(JsonFieldType.STRING).description("user_name of AuthToken"),
								fieldWithPath("authorities").type(JsonFieldType.ARRAY).description("authorities of AuthToken"),
								fieldWithPath("client_id").type(JsonFieldType.STRING).description("client_id of AuthToken"),
								fieldWithPath("scope").type(JsonFieldType.ARRAY).description("scope of AuthToken"))
//						links(linkWithRel("self").description("link to self"))
						));
	}

}
