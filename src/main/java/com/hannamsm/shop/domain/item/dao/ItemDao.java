package com.hannamsm.shop.domain.item.dao;

import java.util.List;
import java.util.Optional;

import com.hannamsm.shop.domain.item.vo.Item;

public interface ItemDao {
	public int findAllTotalCount(Item eventSearch) throws Exception;
	
	public List<Item> findAll(Item eventSearch) throws Exception;
	
	public Optional<Item> findById(Integer id) throws Exception;
	
}
