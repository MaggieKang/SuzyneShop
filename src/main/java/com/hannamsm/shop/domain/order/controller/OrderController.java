package com.hannamsm.shop.domain.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.domain.account.vo.Account;
import com.hannamsm.shop.domain.order.service.OrderService;
import com.hannamsm.shop.domain.order.vo.Order;
import com.hannamsm.shop.domain.order.vo.OrderDetail;
import com.hannamsm.shop.domain.order.vo.OrderSearch;
import com.hannamsm.shop.global.adapter.CurrentUser;
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
	public ResponseEntity queryOrders(@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "listSize", defaultValue = "100") int listSize,
            @CurrentUser Account account) throws Exception {

		OrderSearch orderSearch = new OrderSearch(page, listSize);
		orderSearch.setAccountId(account.getAccountId());

		int allCount = this.orderService.findAllCount(orderSearch);
		List<Order> list = this.orderService.findAll(orderSearch);

		//return data
    	ResponseResutlsByPaging<Order> resResult = new ResponseResutlsByPaging<Order>(page, listSize);
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
			@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "listSize", defaultValue = "100") int listSize,
            @CurrentUser Account account) throws Exception {

		OrderSearch orderSearch = new OrderSearch(page, listSize);
		orderSearch.setAccountId(account.getAccountId());
		orderSearch.setOrderId(orderId);

		int allCount = this.orderService.findByIdCount(orderSearch);
		List<OrderDetail> list = this.orderService.findById(orderSearch);

		//return data
    	ResponseResutlsByPaging<OrderDetail> resResult = new ResponseResutlsByPaging<OrderDetail>(page, listSize);
		resResult.setMessage("조회되었습니다.");
		resResult.setTotalCount(allCount);
        resResult.setCurrentCount(list.size());
        resResult.setResultList(list);
        resResult.update();

        return ResponseEntity.ok(resResult);
	}
}
