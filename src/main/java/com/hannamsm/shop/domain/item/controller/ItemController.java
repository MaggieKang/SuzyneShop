package com.hannamsm.shop.domain.item.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.domain.event.vo.Event;
import com.hannamsm.shop.global.vo.ResponseResutl;
import com.hannamsm.shop.global.vo.ResponseResutlsByPaging;

@RestController
@RequestMapping(value="/api/item", produces = MediaTypes.HAL_JSON_VALUE)
public class ItemController {
	
	//TODO ITEM 목록 조회
	@GetMapping
	public ResponseEntity queryItems(@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "listSize", defaultValue = "100") int listSize) throws Exception {

		//get data
		List<String> list = new ArrayList<String>();
		list.add(new String("sss"));
		
		//return data
    	ResponseResutlsByPaging<String> resResult = new ResponseResutlsByPaging<String>(page, listSize);
		resResult.setMessage("조회되었습니다.");
		resResult.setTotalCount(0);
        resResult.setCurrentCount(0);
        resResult.setResultList(list);
        resResult.update();
		
        return ResponseEntity.ok(resResult);
	}
	
	//TODO 자주가는 매장 조회
	@GetMapping("/{id}")
	public ResponseEntity queryItem(@PathVariable Integer id) throws Exception {
		
		ResponseResutl<Event> result = new ResponseResutl<Event>();
		result.setMessage("조회하였습니다.");
		result.setResult(null);
		return ResponseEntity.ok(result);
	}
}