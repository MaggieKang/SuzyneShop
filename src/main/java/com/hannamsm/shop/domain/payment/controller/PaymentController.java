package com.hannamsm.shop.domain.payment.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.domain.account.vo.Account;
import com.hannamsm.shop.domain.payment.service.PaymentService;
import com.hannamsm.shop.domain.payment.vo.AddPaymentDto;
import com.hannamsm.shop.domain.payment.vo.DeletePaymentDto;
import com.hannamsm.shop.domain.payment.vo.Payment;
import com.hannamsm.shop.domain.payment.vo.PaymentDto;
import com.hannamsm.shop.domain.payment.vo.PaymentSearch;
import com.hannamsm.shop.domain.payment.vo.SavePaymentDto;
import com.hannamsm.shop.global.adapter.CurrentUser;
import com.hannamsm.shop.global.vo.ResponseResult;
import com.hannamsm.shop.global.vo.ResponseResutls;

@RestController
@RequestMapping(value="/api/payment", produces = MediaTypes.HAL_JSON_VALUE)
public class PaymentController {

	@Autowired
	private ModelMapper modelMapper;

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

	/*
	 * Payment 추가
	 */
	@PostMapping
	public ResponseEntity addPayment(@RequestBody @Valid AddPaymentDto addPaymentDto
			, @CurrentUser Account currentUser) throws Exception {
		addPaymentDto.setAccountNo(currentUser.getAccountNo());

		Payment payment = this.modelMapper.map(addPaymentDto, Payment.class);

		this.paymentService.insertPayment(payment);

		ResponseResult<AddPaymentDto> resResult = new ResponseResult<AddPaymentDto>();
		resResult.setMessage("추가 되었습니다.");
		resResult.setResult(addPaymentDto);

        return ResponseEntity.ok(resResult);
	}

	/*
	 * Payment 저장
	 */
	@PutMapping
	public ResponseEntity savePayment(@RequestBody @Valid SavePaymentDto savePaymentDto
			, @CurrentUser Account currentUser) throws Exception {
		savePaymentDto.setAccountNo(currentUser.getAccountNo());

		Payment payment = this.modelMapper.map(savePaymentDto, Payment.class);

		this.paymentService.savePayment(payment);

		ResponseResult<SavePaymentDto> resResult = new ResponseResult<SavePaymentDto>();
		resResult.setMessage("추가 되었습니다.");
		resResult.setResult(savePaymentDto);

        return ResponseEntity.ok(resResult);
	}

	/*
	 * Payment 삭제
	 */
	@DeleteMapping
	public ResponseEntity deletePayment(@RequestBody @Valid DeletePaymentDto deletePaymentDto
			, @CurrentUser Account currentUser) throws Exception {
		deletePaymentDto.setAccountNo(currentUser.getAccountNo());

		Payment payment = this.modelMapper.map(deletePaymentDto, Payment.class);

		this.paymentService.deletePayment(payment);

		ResponseResult<DeletePaymentDto> resResult = new ResponseResult<DeletePaymentDto>();
		resResult.setMessage("삭제 되었습니다.");
		resResult.setResult(deletePaymentDto);

        return ResponseEntity.ok(resResult);
	}
}
