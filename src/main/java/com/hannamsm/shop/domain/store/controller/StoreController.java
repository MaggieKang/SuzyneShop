package com.hannamsm.shop.domain.store.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.domain.store.service.StoreService;
import com.hannamsm.shop.domain.store.vo.Store;
import com.hannamsm.shop.domain.store.vo.StoreSearch;
import com.hannamsm.shop.global.vo.ResponseResult;
import com.hannamsm.shop.global.vo.ResponseResutlsByPaging;

@RestController
@RequestMapping(value="/api/store", produces = MediaTypes.HAL_JSON_VALUE)
public class StoreController {

	@Autowired
	private StoreService storeService;

	/*
	 * 매장 목록 조회
	 */
	@GetMapping
	public ResponseEntity queryStores(@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "listSize", defaultValue = "100") int listSize) throws Exception {
		StoreSearch storeSearch = new StoreSearch(page, listSize);

		int allCount = this.storeService.findAllCount(storeSearch);
		List<Store> list = this.storeService.findAll(storeSearch);

		//return data
    	ResponseResutlsByPaging<Store> resResult = new ResponseResutlsByPaging<Store>(page, listSize);
		resResult.setMessage("조회되었습니다.");
		resResult.setTotalCount(allCount);
        resResult.setCurrentCount(list.size());
        resResult.setResultList(list);
        resResult.update();

        return ResponseEntity.ok(resResult);
	}

	/*
	 * Item 조회
	 */
	@GetMapping("/{code}")
	public ResponseEntity queryItem(@PathVariable String code) throws Exception {

		Optional<Store> optionaStore = this.storeService.findById(code);
		if(optionaStore.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		ResponseResult<Store> result = new ResponseResult<Store>();
		result.setMessage("조회하였습니다.");
		result.setResult(optionaStore.get());
		return ResponseEntity.ok(result);
	}

}
