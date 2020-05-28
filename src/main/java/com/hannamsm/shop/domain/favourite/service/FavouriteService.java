package com.hannamsm.shop.domain.favourite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannamsm.shop.domain.favourite.dao.FavouriteDao;
import com.hannamsm.shop.domain.favourite.vo.FavouriteItem;
import com.hannamsm.shop.domain.favourite.vo.FavouriteItemSearch;

@Service
public class FavouriteService {

	@Autowired
	FavouriteDao favouriteDao;

	public int findByAccountIdCount(FavouriteItemSearch favouriteItemSearch) throws Exception {
		return this.favouriteDao.findByAccountIdCount(favouriteItemSearch);
	}

	public List<FavouriteItem> findByAccountId(FavouriteItemSearch favouriteItemSearch) throws Exception {
		return favouriteDao.findByAccountId(favouriteItemSearch);
	}


}
