package com.suzyne.shop.domain.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

import com.suzyne.shop.domain.event.vo.Event;

public class EventTest {

	@Test
	public void builder() {
		String name = "Event";
		String description = "Rest api development with Spring";
		
		Event event = Event.builder()
				.name(name)
				.description(description)
				.build();
		
		assertNotNull(event);
	}
	
	@Test
	public void javaBean() {
		// Given
		String name = "Event";
		String description = "Rest api development with Spring";

		// When
		Event event = new Event();
		event.setName(name);
		event.setDescription(description);
		
		// Than
//		assertThat(event.getName()).isEqualTo(name);
//		assertThat(event.getDescription()).isEqualTo(description);
		assertEquals(name, event.getName(), "이벤트 이름이 같아야 한다.");
		assertEquals(description, event.getDescription(), "입력됩 설명이 같아야 한다.");
	}
	
	@ParameterizedTest
	@CsvSource({"0,0,true","100,0,false","0,100,false"})
	public void testFree(int basePrice, int maxPrice, boolean isFree) {
		// Given
		Event event = Event.builder()
				.basePrice(basePrice)
				.maxPrice(maxPrice)
				.build();
		
		// When
		event.update();
		
		// Then
		assertEquals(isFree, event.isFree(), ()->"특정 값이 있어 isFree???");
	}
	
	@ParameterizedTest
	@CsvSource({"강남역 2번 출구앞,true",",false"})
	public void testOffline(String location, boolean isOffline) {
		// Given
		Event event = Event.builder()
				.location(location)
				.build();
		
		// When
		event.update();
		System.out.println(event.toString());
		// Then
		assertThat(event.isOffline()).isEqualTo(isOffline);
	}
	
	@Test
	public void pageableTest() {
		Pageable pageable = new Pageable();
		
		System.out.println(pageable.toString());
	}
}
