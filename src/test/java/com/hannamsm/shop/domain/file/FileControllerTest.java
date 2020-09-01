package com.hannamsm.shop.domain.file;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockMultipartFile;

import com.hannamsm.shop.global.BaseControllerTest;

@Disabled
public class FileControllerTest extends BaseControllerTest {

	@Test
	@DisplayName("File 업로드 테스트 - (정상)")
	public void fileUploadTest() throws Exception {
		// Given
		MockMultipartFile file = new MockMultipartFile(
				"file",
				"test.txt",
				"text/plain",
				"hello file222".getBytes());

		// When & Then
		mockMvc.perform(multipart("/api/file/uploadFile").file(file)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
			)
			.andDo(print())
			.andExpect(status().isOk());
	}
}
