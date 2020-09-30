package com.hannamsm.shop.domain.order.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.domain.account.vo.Account;
import com.hannamsm.shop.domain.order.service.OrderService;
import com.hannamsm.shop.domain.order.vo.NewOrderDto;
import com.hannamsm.shop.domain.order.vo.OrderDetailDto;
import com.hannamsm.shop.domain.order.vo.OrderDto;
import com.hannamsm.shop.domain.order.vo.OrderSearch;
import com.hannamsm.shop.domain.order.vo.PayNowOrderDto;
import com.hannamsm.shop.global.adapter.CurrentUser;
import com.hannamsm.shop.global.vo.ResponseResult;
import com.hannamsm.shop.global.vo.ResponseResutlsByPaging;

@RestController
@RequestMapping(value="/api/order", produces = MediaTypes.HAL_JSON_VALUE)
public class OrderController {

	@Autowired
	private OrderService orderService;

	/*
	 * 주문 목록 조회
	 */
	@GetMapping
	public ResponseEntity queryOrders(@RequestParam(value = "storeId", defaultValue = "") String storeId,
			@RequestParam(value = "orderId", defaultValue = "") String orderId,
			@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "listSize", defaultValue = "100") int listSize,
            @CurrentUser Account account) throws Exception {

		OrderSearch orderSearch = new OrderSearch(page, listSize);
		orderSearch.setAccountNo(account.getAccountNo());
		orderSearch.setStoreId(storeId);
		orderSearch.setOrderId(orderId);

		int allCount = this.orderService.findAllCount(orderSearch);
		List<OrderDto> list = this.orderService.findAll(orderSearch);

		//return data
    	ResponseResutlsByPaging<OrderDto> resResult = new ResponseResutlsByPaging<OrderDto>(page, listSize);
		resResult.setMessage("조회되었습니다.");
		resResult.setTotalCount(allCount);
        resResult.setCurrentCount(list.size());
        resResult.setResultList(list);
        resResult.update();

        return ResponseEntity.ok(resResult);
	}

	/*
	 * 주문 상세 조회
	 */
	@GetMapping("/{orderId}")
	public ResponseEntity getOrder(@PathVariable String orderId,
			@RequestParam(value = "storeId", defaultValue = "") String storeId,
			@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "listSize", defaultValue = "100") int listSize,
            @CurrentUser Account account) throws Exception {

		OrderSearch orderSearch = new OrderSearch(page, listSize);
		orderSearch.setAccountNo(account.getAccountNo());
		orderSearch.setStoreId(storeId);
		orderSearch.setOrderId(orderId);

		int allCount = this.orderService.findByIdCount(orderSearch);
		List<OrderDetailDto> list = this.orderService.findById(orderSearch);

		//return data
    	ResponseResutlsByPaging<OrderDetailDto> resResult = new ResponseResutlsByPaging<OrderDetailDto>(page, listSize);
		resResult.setMessage("조회되었습니다.");
		resResult.setTotalCount(allCount);
        resResult.setCurrentCount(list.size());
        resResult.setResultList(list);
        resResult.update();

        return ResponseEntity.ok(resResult);
	}

	/*
	 * 신규 주문 저장
	 */
	@PostMapping
	public ResponseEntity saveNewOrder(@RequestBody @Valid NewOrderDto newOrderDto
			, @CurrentUser Account currentUser) throws Exception {
		newOrderDto.setAccountNo(currentUser.getAccountNo());

		NewOrderDto resultNewOrderDto = this.orderService.saveCartToOrder(newOrderDto);

		ResponseResult<NewOrderDto> resResult = new ResponseResult<NewOrderDto>();
		resResult.setMessage("저장 되었습니다.");
		resResult.setResult(resultNewOrderDto);

        return ResponseEntity.ok(resResult);
	}

	/*
	 * 주문 지불 저장
	 */
	@PostMapping(value = "/paynow/{orderId}")
	public ResponseEntity savePayNowOrder(@RequestBody @Valid PayNowOrderDto payNowOrderDto
			, @CurrentUser Account currentUser) throws Exception {
		payNowOrderDto.setAccountNo(currentUser.getAccountNo());

		PayNowOrderDto resultNewOrderDto = this.orderService.savePayNowOrder(payNowOrderDto);

		ResponseResult<PayNowOrderDto> resResult = new ResponseResult<PayNowOrderDto>();
		resResult.setMessage("저장 되었습니다.");
		resResult.setResult(resultNewOrderDto);
        return ResponseEntity.ok(resResult);
	}
}
