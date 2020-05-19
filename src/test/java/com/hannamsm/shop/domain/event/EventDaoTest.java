package com.hannamsm.shop.domain.event;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hannamsm.shop.domain.event.dao.EventDao;
import com.hannamsm.shop.domain.event.vo.Event;
import com.hannamsm.shop.domain.event.vo.EventSearch;
import com.hannamsm.shop.global.BaseDaoTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EventDaoTest extends BaseDaoTest {

	@Autowired
	private EventDao eventDao;
	
	@Test
	@DisplayName("Event Id 조회 테스트")
	public void selectEventId() throws Exception {
		
		int id = this.eventDao.selectEventId();
		System.out.println("id:" + id);
		assertAll("resultList",
				()->assertNotNull(id));
	}
	
	@Test
	@DisplayName("Event 조회 테스트")
	public void selectEvent() throws Exception {
		EventSearch search = new EventSearch(1, 10);
		
		List<Event> list = this.eventDao.findAll(search);
		System.out.println("id:" + list.toString());
		assertAll("resultList",
				()->assertNotNull(list));
	}
	
	@Test
	@DisplayName("Event 생성 테스트")
	public void createEvent() throws Exception {
		log.info("##### 테스트 #####");
		Event event = Event.builder()
				.id(9998) //pk값
				.name("Spring")
				.description("REST API Development with ")
				.beginEnrollmentDateTime(LocalDateTime.of(2018, 11, 23, 14, 21))
				.closeEnrollmentDateTime(LocalDateTime.of(2018, 11, 24, 14, 21))
				.beginEnrollmentDateTime(LocalDateTime.of(2018, 11, 25, 14, 21))
				.endEventDateTime(LocalDateTime.of(2018, 11, 26, 14, 21))
				.basePrice(100)
				.maxPrice(200)
				.limitOfEnrollment(100) //계산
				.location("Kang Way Station")
				.free(true) //계산
				.offline(false) //계산
				.build();
		
		int cnt = this.eventDao.create(event);
		System.out.println("cnt:" + cnt);
		assertAll("resultList",
				()->assertNotNull(event));
	}
	
	@Test
	@DisplayName("Event 변경 테스트")
	public void saveEvent() throws Exception {
		log.info("##### 테스트 #####");
		Event event = Event.builder()
				.id(20) //pk값
				.name("Spring dddd")
				.description("REST API Development with ")
				.beginEnrollmentDateTime(LocalDateTime.of(2018, 11, 23, 14, 21))
				.closeEnrollmentDateTime(LocalDateTime.of(2018, 11, 24, 14, 21))
				.beginEnrollmentDateTime(LocalDateTime.of(2018, 11, 25, 14, 21))
				.endEventDateTime(LocalDateTime.of(2018, 11, 26, 14, 21))
				.basePrice(100)
				.maxPrice(200)
				.limitOfEnrollment(100) //계산
				.location("Kang Way Station")
				.free(true) //계산
				.offline(false) //계산
				.build();
		
		int cnt = this.eventDao.update(event);
		System.out.println("cnt:" + cnt);
		assertAll("resultList",
				()->assertNotNull(event));
	}
}
