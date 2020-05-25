package com.hannamsm.shop.domain.item.dao;

import java.util.List;
import java.util.Optional;

import com.hannamsm.shop.domain.item.vo.Item;
import com.hannamsm.shop.domain.item.vo.ItemSearch;

public interface ItemDao {
	public int findAllCount(ItemSearch itemSearch) throws Exception;

	public List<Item> findAll(ItemSearch itemSearch) throws Exception;

	public Optional<Item> findById(String id) throws Exception;

	public List<Item> findByName(ItemSearch itemSearch) throws Exception;

}
