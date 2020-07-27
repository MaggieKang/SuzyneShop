package com.hannamsm.shop.domain.notification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hannamsm.shop.domain.event.vo.Event;
import com.hannamsm.shop.global.vo.ResponseResult;

public class NotificationController {

	//TODO 비밀번호 리셋이메일
	@GetMapping("/{id}")
	public ResponseEntity sendResetEmail(@PathVariable Integer id) throws Exception {
		
		ResponseResult<Event> result = new ResponseResult<Event>();
		result.setMessage("조회하였습니다.");
		result.setResult(null);
		return ResponseEntity.ok(result);
	}
	
	//TODO 회원인증이메일 (환영메일 발송)
	@GetMapping("/{id}")
	public ResponseEntity sendCheckEmail(@PathVariable Integer id) throws Exception {
		
		ResponseResult<Event> result = new ResponseResult<Event>();
		result.setMessage("조회하였습니다.");
		result.setResult(null);
		return ResponseEntity.ok(result);
	}

	//TODO 주문완료 메일 발송
	@GetMapping("/{id}")
	public ResponseEntity sendCompleteOrder(@PathVariable Integer id) throws Exception {
		
		ResponseResult<Event> result = new ResponseResult<Event>();
		result.setMessage("조회하였습니다.");
		result.setResult(null);
		return ResponseEntity.ok(result);
	}
	
	//TODO 결제완료 메일 발송 
	@GetMapping("/{id}")
	public ResponseEntity sendCompletePayment(@PathVariable Integer id) throws Exception {
		
		ResponseResult<Event> result = new ResponseResult<Event>();
		result.setMessage("조회하였습니다.");
		result.setResult(null);
		return ResponseEntity.ok(result);
	}
}
