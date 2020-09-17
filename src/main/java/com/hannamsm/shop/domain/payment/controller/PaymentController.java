package com.hannamsm.shop.domain.payment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.domain.account.vo.Account;
import com.hannamsm.shop.domain.payment.service.PaymentService;
import com.hannamsm.shop.domain.payment.vo.PaymentDto;
import com.hannamsm.shop.domain.payment.vo.PaymentSearch;
import com.hannamsm.shop.global.adapter.CurrentUser;
import com.hannamsm.shop.global.vo.ResponseResult;
import com.hannamsm.shop.global.vo.ResponseResutls;

@RestController
@RequestMapping(value="/api/payment", produces = MediaTypes.HAL_JSON_VALUE)
public class PaymentController {

//	@Autowired
//	private ModelMapper modelMapper;

	@Autowired
	private PaymentService paymentService;

	/*
	 * Payment 목록 조회
	 */
	@GetMapping
	public ResponseEntity getMyPayment(@CurrentUser Account account) throws Exception {

		PaymentSearch paymentSearch = new PaymentSearch();
		paymentSearch.setAccountNo(account.getAccountNo());

		List<PaymentDto> list = this.paymentService.find(paymentSearch);

		//return data
		ResponseResutls<PaymentDto> resResult = new ResponseResutls<PaymentDto>();
		resResult.setMessage("조회되었습니다.");
        resResult.setResultList(list);

        return ResponseEntity.ok(resResult);
	}

	/*
	 * Payment Default 조회
	 */
	@GetMapping("/default")
	public ResponseEntity getMyDefaultPayment(@CurrentUser Account account) throws Exception {

		PaymentSearch paymentSearch = new PaymentSearch();
		paymentSearch.setAccountNo(account.getAccountNo());

		PaymentDto result = this.paymentService.findByDefault(paymentSearch);

		//return data
		ResponseResult<PaymentDto> resResult = new ResponseResult<PaymentDto>();
		resResult.setMessage("조회되었습니다.");
        resResult.setResult(result);

        return ResponseEntity.ok(resResult);
	}
}
