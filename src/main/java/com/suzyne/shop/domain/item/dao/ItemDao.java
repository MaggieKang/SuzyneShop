package com.suzyne.shop.domain.item.dao;

import java.util.List;
import java.util.Optional;

import com.suzyne.shop.domain.item.vo.Item;
import com.suzyne.shop.domain.item.vo.ItemDto;
import com.suzyne.shop.domain.item.vo.ItemForAddCart;
import com.suzyne.shop.domain.item.vo.ItemForAddCartSearch;
import com.suzyne.shop.domain.item.vo.ItemSearch;
import com.suzyne.shop.global.mapper.HnsShopConnMapper;

@HnsShopConnMapper
public interface ItemDao {
	public int findAllCount(ItemSearch itemSearch) throws Exception;

	public List<Item> findAll(ItemSearch itemSearch) throws Exception;

	public Optional<ItemDto> findById(ItemSearch itemSearch) throws Exception;

	public List<Item> findByName(ItemSearch itemSearch) throws Exception;

	public Optional<ItemForAddCart> findForAddCart(ItemForAddCartSearch itemForAddCartSearch) throws Exception;

}
