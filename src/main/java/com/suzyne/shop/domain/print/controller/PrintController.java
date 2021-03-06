package com.suzyne.shop.domain.print.controller;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suzyne.shop.domain.event.vo.Event;
import com.suzyne.shop.global.vo.ResponseResult;

@RestController
@RequestMapping(value="/api/print", produces = MediaTypes.HAL_JSON_VALUE)
public class PrintController {
	
	//TODO 주문 프린트
	@GetMapping("/order/{id}")
	public ResponseEntity printOrder(@PathVariable Integer id) throws Exception {
		
		ResponseResult<Event> result = new ResponseResult<Event>();
		result.setMessage("조회하였습니다.");
		result.setResult(null);
		return ResponseEntity.ok(result);
	}
	//TODO 인보이스 프린트
	@GetMapping("/invoice/{id}")
	public ResponseEntity queryInvoice(@PathVariable Integer id) throws Exception {
		
		ResponseResult<Event> result = new ResponseResult<Event>();
		result.setMessage("조회하였습니다.");
		result.setResult(null);
		return ResponseEntity.ok(result);
	}
}
