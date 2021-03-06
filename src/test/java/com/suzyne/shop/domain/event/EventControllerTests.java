package com.suzyne.shop.domain.event;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.suzyne.shop.domain.event.dao.EventDao;
import com.suzyne.shop.domain.event.vo.Event;
import com.suzyne.shop.domain.event.vo.EventDto;
import com.suzyne.shop.domain.event.vo.EventStatus;
import com.suzyne.shop.global.BaseControllerTest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Disabled
public class EventControllerTests extends BaseControllerTest {

	@Autowired
	EventDao eventDao;

	@Autowired
	ModelMapper modelMapper;

	/*
	 * ?????? ????????? ???????????? json ???????????? 201??? ???????????? ??????.
	 * location ????????? ????????? ???????????? ????????? ??? ?????? URI ?????? ????????? ??????.
	 * id??? DB??? ????????? ??? ??????????????? ????????? ???????????? ??????
	 */
	@Test
	@DisplayName("??????????????? EVENT??? ???????????? ?????????")
	public void createEvent() throws Exception {
		// Given
		EventDto event = EventDto.builder()
			.name("Spring")
			.description("REST API Development with ")
			.beginEnrollmentDateTime(LocalDateTime.of(2018, 11, 23, 14, 21))
			.closeEnrollmentDateTime(LocalDateTime.of(2018, 11, 24, 14, 21))
			.beginEventDateTime(LocalDateTime.of(2018, 11, 25, 14, 21))
			.endEventDateTime(LocalDateTime.of(2018, 11, 26, 14, 21))
			.basePrice(100)
			.maxPrice(200)
			.limitOfEnrollment(100) //??????
			.location("Kangs Way Station")
			.build();

		// When
		mockMvc.perform(post("/api/events/")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
				.content(this.objectMapper.writeValueAsString(event)))
			.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(header().exists(HttpHeaders.LOCATION))
			.andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
			.andExpect(jsonPath("id").exists())
			.andExpect(jsonPath("free").value(false))
			.andExpect(jsonPath("offline").value(true))
			.andExpect(jsonPath("eventStatus").value(EventStatus.DRAFT.name()))
			.andDo(document("create-event",
					requestHeaders(
							headerWithName(HttpHeaders.ACCEPT).description("accept header"),
							headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
					),
					requestFields(
							fieldWithPath("name").description("Name of new event"),
							fieldWithPath("description").description("Description of new event"),
							fieldWithPath("beginEnrollmentDateTime").description("date time of begin enrollment of new event"),
							fieldWithPath("closeEnrollmentDateTime").description("date time of close enrollment of new event"),
							fieldWithPath("beginEventDateTime").description("date time of begin of new event"),
							fieldWithPath("endEventDateTime").description("date tme of end of new event"),
							fieldWithPath("location").description("location of new event"),
							fieldWithPath("basePrice").description("basePrice of new event"),
							fieldWithPath("maxPrice").description("maxPrice of new event"),
							fieldWithPath("limitOfEnrollment").description("limit of enrollment of new event")
					),
					responseHeaders(
							headerWithName(HttpHeaders.LOCATION).description("location header"),
							headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
					),
					responseFields(
							fieldWithPath("id").description("id of new event"),
							fieldWithPath("name").description("name of new event"),
							fieldWithPath("description").description("description of new event"),
							fieldWithPath("beginEnrollmentDateTime").description("date time of begin enrollment of new event"),
							fieldWithPath("closeEnrollmentDateTime").description("date time of close enrollment of new event"),
							fieldWithPath("beginEventDateTime").description("date time of begin of new event"),
							fieldWithPath("endEventDateTime").description("date tme of end of new event"),
							fieldWithPath("location").description("location of new event"),
							fieldWithPath("basePrice").description("basePrice of new event"),
							fieldWithPath("maxPrice").description("maxPrice of new event"),
							fieldWithPath("limitOfEnrollment").description("limit of enrollment of new event"),
							fieldWithPath("free").description("it tells if this event is free"),
							fieldWithPath("offline").description("it tells if this event is offline"),
							fieldWithPath("eventStatus").description("event status")
					)
				)
			);
	}

	@Test
	@DisplayName("EVENT ?????? Bad_Request_Empty_Input ????????? ??????")
	public void createEvent_Bad_Request_Empty_Input() throws Exception {
		EventDto event = EventDto.builder().build();

		//Mockito??? ???????????? mock ????????? ????????? ????????? ????????? ???.
		//(??????) ?????? ?????? ???????????? ?????? ?????? ??????.
		//Mockito.when(eventService.save(event)).thenReturn(event);

		mockMvc.perform(post("/api/events/")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
				.content(this.objectMapper.writeValueAsString(event)))
			.andDo(print())
			.andExpect(status().isBadRequest())
			;
	}

	@Test
	@DisplayName("EVENT ?????? Bad_Request_Wrong_Input ????????? ??????")
	public void createEvent_Bad_Request_Wrong_Input() throws Exception {
		EventDto event = EventDto.builder()
			.name("Spring")
			.description("REST API Development with ")
			.beginEnrollmentDateTime(LocalDateTime.of(2018, 11, 26, 14, 21))
			.closeEnrollmentDateTime(LocalDateTime.of(2018, 11, 24, 14, 21))
			.beginEventDateTime(LocalDateTime.of(2018, 11, 25, 14, 21))
			.endEventDateTime(LocalDateTime.of(2018, 11, 26, 14, 21))
			.basePrice(10000)
			.maxPrice(200)
			.limitOfEnrollment(100) //??????
			.location("Kang Way Station")
			.build();

		//Mockito??? ???????????? mock ????????? ????????? ????????? ????????? ???.
		//(??????) ?????? ?????? ???????????? ?????? ?????? ??????.
		//Mockito.when(eventService.save(event)).thenReturn(event);

		mockMvc.perform(post("/api/events/")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
				.content(this.objectMapper.writeValueAsString(event)))
			.andDo(print())
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("content[0].objectName").exists())
			.andExpect(jsonPath("content[0].defaultMessage").exists())
			.andExpect(jsonPath("content[0].code").exists())
//			.andExpect(jsonPath("$[0].field").exists())
//			.andExpect(jsonPath("$[0].rejectedValue").exists())
			;
	}

	@Test
	@DisplayName("????????? ????????? 10?????? ????????? ????????? ????????????")
	public void queryEvents() throws Exception {
		//Given

		//When
		this.mockMvc.perform(get("/api/events")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
				.param("page", "2")
				.param("listSize", "10")
			)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("page").exists())
			.andExpect(jsonPath("listSize").exists())
			.andDo(document("query-events"))
			;
	}

	@Test
	@DisplayName("???????????? ID??? ??????????????? ???????????? ?????????")
	public void getEvent() throws Exception {
		// Given
		Event event = new Event();
		event.setId(1);

		// When & Then
		this.mockMvc.perform(get("/api/events/{id}", event.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
				)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("result.id").exists())
			.andExpect(jsonPath("result.name").exists())
			.andDo(document("query-eventById"))
			;
	}

	@Test
	@DisplayName("?????? ???????????? ID??? ???????????? ??? 404 ??????")
	public void getEvent404() throws Exception {
		// Given
		Event event = new Event();
		event.setId(999999);

		// When & Then
		this.mockMvc.perform(get("/api/events/{id}", event.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
			)
			.andDo(print())
			.andExpect(status().isNotFound())
			;
	}

	@Test
	@DisplayName("???????????? ??????????????? ????????????")
	public void updateEvent() throws Exception {
		String eventName = "Updated Event";
		Event event = this.generateEvent(1);

		EventDto eventDto = this.modelMapper.map(event, EventDto.class);
		eventDto.setName(eventName);

		this.mockMvc.perform(put("/api/events/{id}", event.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
				.content(this.objectMapper.writeValueAsString(eventDto))
			)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("result.name").value(eventName));
	}

	@Test
	@DisplayName("???????????? ???????????? ????????? ????????? ?????? ??????")
	public void updateEvent400_Empty() throws Exception {
		Event event = this.generateEvent(1);

		EventDto eventDto = this.modelMapper.map(event, EventDto.class);
		eventDto.setBeginEnrollmentDateTime(null);

		this.mockMvc.perform(put("/api/events/{id}", event.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
				.content(this.objectMapper.writeValueAsString(eventDto))
			)
			.andDo(print())
			.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("???????????? ????????? ????????? ????????? ?????? ??????")
	public void updateEvent400_Wrong() throws Exception {
		Event event = this.generateEvent(1);

		EventDto eventDto = this.modelMapper.map(event, EventDto.class);
		eventDto.setBasePrice(20000);
		eventDto.setMaxPrice(1000);

		this.mockMvc.perform(put("/api/events/{id}", event.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
				.content(this.objectMapper.writeValueAsString(eventDto))
			)
			.andDo(print())
			.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("???????????? ?????? ????????? ?????? ??????")
	public void updateEvent404_NotFound() throws Exception {
		Event event = this.generateEvent(200);

		EventDto eventDto = this.modelMapper.map(event, EventDto.class);
		eventDto.setBasePrice(20000);
		eventDto.setMaxPrice(1000);

		this.mockMvc.perform(put("/api/events/999999")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.header(HttpHeaders.AUTHORIZATION, super.getBearerToken())
				.content(this.objectMapper.writeValueAsString(eventDto))
				)
			.andDo(print())
			.andExpect(status().isNotFound());
	}

	private Event generateEvent(int index) {
		Event event = Event.builder()
				.id(index)
				.name("Spring "+index)
				.description("REST API Development with ")
				.beginEnrollmentDateTime(LocalDateTime.of(2018, 11, 23, 14, 21))
				.closeEnrollmentDateTime(LocalDateTime.of(2018, 11, 24, 14, 21))
				.beginEventDateTime(LocalDateTime.of(2018, 11, 25, 14, 21))
				.endEventDateTime(LocalDateTime.of(2018, 11, 26, 14, 21))
				.basePrice(100)
				.maxPrice(200)
				.limitOfEnrollment(100) //??????
				.location("Kangs Way Station")
				.build();
		return event;
	}

}
