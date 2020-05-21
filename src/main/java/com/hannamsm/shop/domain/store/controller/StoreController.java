package com.hannamsm.shop.domain.store.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.global.vo.ResponseResutlsByPaging;

@RestController
@RequestMapping(value="/api/store", produces = MediaTypes.HAL_JSON_VALUE)
public class StoreController {
	
	//TODO 매장 목록 조회
	@GetMapping
	public ResponseEntity queryStores(@RequestParam(value = "page", defaultValue = "1") int page,
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

}
