package com.hannamsm.shop.domain.favourite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.domain.account.vo.Account;
import com.hannamsm.shop.domain.favourite.service.FavouriteService;
import com.hannamsm.shop.domain.favourite.vo.FavouriteItem;
import com.hannamsm.shop.domain.favourite.vo.FavouriteItemSearch;
import com.hannamsm.shop.global.adapter.CurrentUser;
import com.hannamsm.shop.global.vo.ResponseResutlsByPaging;

@RestController
@RequestMapping(value="/api/favourite", produces = MediaTypes.HAL_JSON_VALUE)
public class FavouriteController {

	@Autowired
	FavouriteService favouriteService;

	/*
	 * 즐겨찾기 상품 목록 조회
	 */
	@GetMapping
	public ResponseEntity queryFavourites(@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "listSize", defaultValue = "100") int listSize,
            @CurrentUser Account account) throws Exception {
		FavouriteItemSearch favouriteItemSearch = new FavouriteItemSearch(page, listSize);
		favouriteItemSearch.setAccountId(account.getAccountId());

		int allCount = this.favouriteService.findByAccountIdCount(favouriteItemSearch);
		List<FavouriteItem> list = this.favouriteService.findByAccountId(favouriteItemSearch);

		//return data
    	ResponseResutlsByPaging<FavouriteItem> resResult = new ResponseResutlsByPaging<FavouriteItem>(page, listSize);
		resResult.setMessage("조회되었습니다.");
		resResult.setTotalCount(allCount);
        resResult.setCurrentCount(list.size());
        resResult.setResultList(list);
        resResult.update();

        return ResponseEntity.ok(resResult);
	}
}
