package com.suzyne.shop.domain.event.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;

import com.suzyne.shop.domain.event.vo.Event;
import com.suzyne.shop.domain.event.vo.EventSearch;
import com.suzyne.shop.global.mapper.HnsShopConnMapper;

@HnsShopConnMapper
public interface EventDao {
	public int create(@Param("event")Event event) throws Exception;

	public int selectEventId() throws Exception;

	public Optional<Event> findById(int id) throws Exception;

	public int findAllTotalCount(EventSearch eventSearch) throws Exception;

	public List<Event> findAll(EventSearch eventSearch) throws Exception;

	public int update(Event event) throws Exception;

}
