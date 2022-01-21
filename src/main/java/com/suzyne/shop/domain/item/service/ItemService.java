package com.suzyne.shop.domain.item.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suzyne.shop.domain.item.dao.ItemDao;
import com.suzyne.shop.domain.item.vo.Item;
import com.suzyne.shop.domain.item.vo.ItemDto;
import com.suzyne.shop.domain.item.vo.ItemSearch;

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

	public Optional<ItemDto> findById(ItemSearch itemSearch) throws Exception {
		return this.itemDao.findById(itemSearch);
	}

}
