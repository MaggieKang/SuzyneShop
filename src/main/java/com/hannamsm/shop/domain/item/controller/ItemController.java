package com.hannamsm.shop.domain.item.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.domain.item.service.ItemService;
import com.hannamsm.shop.domain.item.vo.Item;
import com.hannamsm.shop.domain.item.vo.ItemSearch;
import com.hannamsm.shop.global.vo.ResponseResult;
import com.hannamsm.shop.global.vo.ResponseResutlsByPaging;


@RestController
@RequestMapping(value="/api/item", produces = MediaTypes.HAL_JSON_VALUE)
public class ItemController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ItemService itemService;

	/*
	 * Item 목록 조회
	 */
	@GetMapping
	public ResponseEntity queryItems(@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "listSize", defaultValue = "100") int listSize,
            @RequestParam(value = "storeId", defaultValue = "") String storeId,
            @RequestParam(value = "itemName", defaultValue = "") String itemName,
            @RequestParam(value = "itemCategory", defaultValue = "") String itemCategory,
            @RequestParam(value = "itemSort", defaultValue = "O0") String itemSort) throws Exception {
		log.debug("================= queryItems start =================");


		if(storeId == null || storeId.isEmpty()) {
			throw new Exception("storeId is null!!!");
		}

		ItemSearch itemSearch = ItemSearch.builder()
				.page(page)
				.listSize(listSize)
				.storeId(storeId)
				.itemName(itemName)
				.itemCategory(itemCategory)
				.itemSort(itemSort)
				.build();
		itemSearch.updatePaging();

		int allCount = this.itemService.findAllCount(itemSearch);
		List<Item> list = this.itemService.findAll(itemSearch);

		//return data
    	ResponseResutlsByPaging<Item> resResult = new ResponseResutlsByPaging<Item>(page, listSize);
		resResult.setMessage("조회되었습니다.");
		resResult.setTotalCount(allCount);
        resResult.setCurrentCount(list.size());
        resResult.setResultList(list);
        resResult.update();

        log.debug("================= queryItems end =================");
        return ResponseEntity.ok(resResult);
	}

	/*
	 * Item 조회
	 */
	@GetMapping("/{id}")
	public ResponseEntity queryItem(@PathVariable String id,
			@RequestParam(value = "storeId", defaultValue = "") String storeId) throws Exception {
		if(storeId == null || storeId.isEmpty()) {
			throw new Exception("Store id is null!!!");
		}

		ItemSearch itemSearch = ItemSearch.builder()
				.storeId(storeId)
				.itemId(id)
				.build();

		Optional<Item> optionalResult = this.itemService.findById(itemSearch);
		if(optionalResult.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		ResponseResult<Item> result = new ResponseResult<Item>();
		result.setMessage("조회하였습니다.");
		result.setResult(optionalResult.get());
		return ResponseEntity.ok(result);
	}
}
