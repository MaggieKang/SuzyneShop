package com.hannamsm.shop.domain.favourite.dao;

import java.util.List;

import com.hannamsm.shop.domain.favourite.vo.FavouriteItem;
import com.hannamsm.shop.domain.favourite.vo.FavouriteItemSearch;

public interface FavouriteDao {
	public int findByAccountIdCount(FavouriteItemSearch favouriteItemSearch) throws Exception;

	public List<FavouriteItem> findByAccountId(FavouriteItemSearch favouriteItemSearch) throws Exception;

	public int add(FavouriteItem favouriteItem) throws Exception;

	public int delete(FavouriteItem favouriteItem) throws Exception;
}
