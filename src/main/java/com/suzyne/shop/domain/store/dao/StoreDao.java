package com.suzyne.shop.domain.store.dao;

import java.util.List;
import java.util.Optional;

import com.suzyne.shop.domain.store.vo.Store;
import com.suzyne.shop.domain.store.vo.StoreSearch;
import com.suzyne.shop.global.mapper.HnsShopConnMapper;

@HnsShopConnMapper
public interface StoreDao {
	public int findAllCount(StoreSearch storeSearch) throws Exception;

	public List<Store> findAll(StoreSearch storeSearch) throws Exception;

	public Optional<Store> findById(String id) throws Exception;

}
