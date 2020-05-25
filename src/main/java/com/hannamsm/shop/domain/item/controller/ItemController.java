package com.hannamsm.shop.domain.item.controller;

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

import com.hannamsm.shop.domain.item.service.ItemService;
import com.hannamsm.shop.domain.item.vo.Item;
import com.hannamsm.shop.domain.item.vo.ItemSearch;
import com.hannamsm.shop.global.vo.ResponseResutl;
import com.hannamsm.shop.global.vo.ResponseResutlsByPaging;

@RestController
@RequestMapping(value="/api/item", produces = MediaTypes.HAL_JSON_VALUE)
public class ItemController {

	@Autowired
	private ItemService itemService;

	/*
	 * Item 목록 조회
	 */
	@GetMapping
	public ResponseEntity queryItems(@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "listSize", defaultValue = "100") int listSize) throws Exception {
		ItemSearch itemSearch = new ItemSearch(page, listSize);

		int allCount = this.itemService.findAllCount(itemSearch);
		List<Item> list = this.itemService.findAll(itemSearch);

		//return data
    	ResponseResutlsByPaging<Item> resResult = new ResponseResutlsByPaging<Item>(page, listSize);
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
	@GetMapping("/{id}")
	public ResponseEntity queryItem(@PathVariable String id) throws Exception {

		Optional<Item> optionalItem = this.itemService.findById(id);
		if(optionalItem.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		ResponseResutl<Item> result = new ResponseResutl<Item>();
		result.setMessage("조회하였습니다.");
		result.setResult(optionalItem.get());
		return ResponseEntity.ok(result);
	}
}
