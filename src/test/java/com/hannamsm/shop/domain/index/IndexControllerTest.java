package com.hannamsm.shop.domain.index;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import com.hannamsm.shop.global.BaseControllerTest;

public class IndexControllerTest extends BaseControllerTest {
	
	@Test
	public void index() throws Exception {
		this.mockMvc.perform(get("/api/"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("_links.events").exists());
	}

}
