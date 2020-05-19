package com.hannamsm.shop.domain.event.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;

import com.hannamsm.shop.domain.event.vo.Event;
import com.hannamsm.shop.domain.event.vo.EventSearch;

public interface EventDao {
	public int create(@Param("event")Event event) throws Exception;

	public int selectEventId() throws Exception;
	
	public Optional<Event> findById(Integer id) throws Exception;

	public int findAllTotalCount(EventSearch eventSearch) throws Exception;
	
	public List<Event> findAll(EventSearch eventSearch) throws Exception;

	public int update(Event event) throws Exception;

}
