package com.hannamsm.shop.domain.item.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannamsm.shop.domain.item.dao.ItemDao;
import com.hannamsm.shop.domain.item.vo.Item;
import com.hannamsm.shop.domain.item.vo.ItemSearch;

@Service
public class ItemService {

	@Autowired
	ItemDao itemDao;

	public List<Item> findAll(ItemSearch itemSearch) throws Exception {
		return itemDao.findAll(itemSearch);
	}

	public int findAllCount(ItemSearch itemSearch) throws Exception {
		return this.itemDao.findAllCount(itemSearch);
	}

	public Optional<Item> findById(ItemSearch itemSearch) throws Exception {
		return this.itemDao.findById(itemSearch);
	}

}
