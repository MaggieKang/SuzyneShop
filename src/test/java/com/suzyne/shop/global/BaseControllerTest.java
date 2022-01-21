package com.suzyne.shop.global;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.oauth2.common.util.Jackson2JsonParser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suzyne.shop.global.properties.AppProperties;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
//@ActiveProfiles("test")
@Import(RestDocsConfiguration.class)
@Disabled
public class BaseControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
    private WebApplicationContext ctx;

	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	AppProperties appProperties;

	protected void init() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
				.addFilters(new CharacterEncodingFilter("UTF-8", true))
				.alwaysDo(print())
				.build();
	}

	protected String getBearerToken() throws Exception {
		//init();
		return getBearerToken(true);
	}

	protected String getAccessToken() throws Exception {
		return getAccessToken(true);
	}

	private String getBearerToken(boolean needToCreateAccount) throws Exception {
		return "Bearer " + getAccessToken(needToCreateAccount);
	}

	private String getAccessToken(boolean needToCreateAccount) throws Exception {
		// Given
		if (needToCreateAccount) {
			// createAccount();
		}

		ResultActions perform = this.mockMvc.perform(
				post("/api/account/login").with(httpBasic(appProperties.getClientId(), appProperties.getClientSecret()))
						.param("username", appProperties.getTestUsername())
						.param("password", appProperties.getTestPassword())
						.param("grant_type", "password"));

		MockHttpServletResponse response = perform.andReturn().getResponse();
		String responseBody = response.getContentAsString();

		Jackson2JsonParser parser = new Jackson2JsonParser();
		return parser.parseMap(responseBody).get("access_token").toString();
	}

	protected String getRefreshToken() throws Exception {

		ResultActions perform = this.mockMvc.perform(
				post("/api/account/login").with(httpBasic(appProperties.getClientId(), appProperties.getClientSecret()))
						.param("username", appProperties.getTestUsername())
						.param("password", appProperties.getTestPassword())
						.param("grant_type", "password"));

		MockHttpServletResponse response = perform.andReturn().getResponse();
		String responseBody = response.getContentAsString();

		Jackson2JsonParser parser = new Jackson2JsonParser();
		return parser.parseMap(responseBody).get("refresh_token").toString();
	}

}
