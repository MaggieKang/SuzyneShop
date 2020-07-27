package com.hannamsm.shop.domain.itemimage.controller;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.domain.event.vo.Event;
import com.hannamsm.shop.global.vo.ResponseResult;

@RestController
@RequestMapping(value="/api/item-image", produces = MediaTypes.HAL_JSON_VALUE)
public class ItemImageController {
	
	//TODO 상품 이미지 조회
	@GetMapping("/{id}")
	public ResponseEntity getItemImage(@PathVariable Integer id) throws Exception {
		
		ResponseResult<Event> result = new ResponseResult<Event>();
		result.setMessage("조회하였습니다.");
		result.setResult(null);
		return ResponseEntity.ok(result);
	}

}
