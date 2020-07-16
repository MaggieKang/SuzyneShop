package com.hannamsm.shop.domain.store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannamsm.shop.domain.store.dao.StoreDao;
import com.hannamsm.shop.domain.store.vo.Store;
import com.hannamsm.shop.domain.store.vo.StoreSearch;

@Service
public class StoreService {

	@Autowired
	StoreDao storeDao;

	public List<Store> findAll(StoreSearch storeSearch) throws Exception {
		return storeDao.findAll(storeSearch);
	}

	public int findAllCount(StoreSearch storeSearch) throws Exception {
		return this.storeDao.findAllCount(storeSearch);
	}

	public Optional<Store> findById(String id) throws Exception {
		return this.storeDao.findById(id);
	}

}
