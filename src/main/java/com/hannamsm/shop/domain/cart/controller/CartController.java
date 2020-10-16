package com.hannamsm.shop.domain.cart.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.domain.account.vo.Account;
import com.hannamsm.shop.domain.cart.service.CartService;
import com.hannamsm.shop.domain.cart.vo.CartAddItemDto;
import com.hannamsm.shop.domain.cart.vo.CartDeleteItemDto;
import com.hannamsm.shop.domain.cart.vo.CartItemDto;
import com.hannamsm.shop.domain.cart.vo.CartItemSearch;
import com.hannamsm.shop.domain.cart.vo.CartSummary;
import com.hannamsm.shop.global.adapter.CurrentUser;
import com.hannamsm.shop.global.vo.ResponseResult;
import com.hannamsm.shop.global.vo.ResponseResultsByPaging;

@RestController
@RequestMapping(value="/api/cart", produces = MediaTypes.HAL_JSON_VALUE)
public class CartController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CartService cartService;

	/*
	 * 장바구니 목록 조회
	 */
	@GetMapping
	public ResponseEntity queryCartItems(@RequestParam(value = "storeId", defaultValue = "") String storeId,
			@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "listSize", defaultValue = "100") int listSize,
            @CurrentUser Account account) throws Exception {
		if(storeId==null || storeId.isEmpty()) {
			throw new Exception("storeId is null!!!");
		}

		CartItemSearch cartItemSearch = new CartItemSearch(page, listSize);
		cartItemSearch.setAccountNo(account.getAccountNo());
		cartItemSearch.setStoreId(storeId);

		int allCount = this.cartService.findByAccountIdCount(cartItemSearch);
		List<CartItemDto> list = this.cartService.findByAccountId(cartItemSearch);

		//return data
    	ResponseResultsByPaging<CartItemDto> resResult = new ResponseResultsByPaging<CartItemDto>(page, listSize);
		resResult.setMessage("조회되었습니다.");
		resResult.setTotalCount(allCount);
        resResult.setCurrentCount(list.size());
        resResult.setResultList(list);
        resResult.update();

        return ResponseEntity.ok(resResult);
	}

	/*
	 * 장바구니 건수 조회
	 */
	@GetMapping("/count")
	public ResponseEntity queryCartCount(@RequestParam(value = "storeId", defaultValue = "") String storeId,
            @CurrentUser Account account) throws Exception {
		if(storeId==null || storeId.isEmpty()) {
			throw new Exception("storeId is null!!!");
		}

		CartItemSearch cartItemSearch = CartItemSearch.builder()
				.accountNo(account.getAccountNo())
				.storeId(storeId)
				.build();

		int allCount = this.cartService.findByAccountIdCount(cartItemSearch);

		//return data
        ResponseResult<Integer> resResult = new ResponseResult<Integer>();
		resResult.setMessage("조회되었습니다.");
		resResult.setResult(allCount);

        return ResponseEntity.ok(resResult);
	}

	// 장바구니 Summary 조회
	@GetMapping("/summary")
	public ResponseEntity queryCartSummary(@RequestParam(value = "storeId", defaultValue = "") String storeId,
			@CurrentUser Account account) throws Exception {
		if(storeId==null || storeId.isEmpty()) {
			throw new Exception("storeId is null!!!");
		}

		CartItemSearch cartItemSearch = new CartItemSearch();
		cartItemSearch.setAccountNo(account.getAccountNo());
		cartItemSearch.setStoreId(storeId);

		CartSummary cartSummary = this.cartService.findSummaryByAccountId(cartItemSearch);

		ResponseResult<CartSummary> result = new ResponseResult<CartSummary>();
		result.setMessage("조회하였습니다.");
		result.setResult(cartSummary);
		return ResponseEntity.ok(result);
	}

	/*
	 * 장바구니 상품 추가
	 */
	@PostMapping(value = "/{itemId}", produces = MediaTypes.HAL_JSON_VALUE)
	public ResponseEntity addCartItem(@PathVariable String itemId
			, @RequestBody @Valid CartAddItemDto cartAddItemDto
			, @CurrentUser Account currentUser) throws Exception {
		cartAddItemDto.setAccountNo(currentUser.getAccountNo());

		CartItemDto cartItemDto = modelMapper.map(cartAddItemDto, CartItemDto.class);

		cartService.addCartItem(cartItemDto);

		ResponseResult<CartAddItemDto> resResult = new ResponseResult<CartAddItemDto>();
		resResult.setMessage("추가 되었습니다.");
		resResult.setResult(cartAddItemDto);

        return ResponseEntity.ok(resResult);
	}

	/*
	 * 장바구니 상품 저장
	 */
	@PutMapping(value = "/{itemId}", produces = MediaTypes.HAL_JSON_VALUE)
	public ResponseEntity saveCartItemQty(@PathVariable String itemId
			, @RequestBody @Valid CartItemDto reqCartItemDto
			, @CurrentUser Account currentUser) throws Exception {
		reqCartItemDto.setAccountNo(currentUser.getAccountNo());

		String message = "";

		if(0 == reqCartItemDto.getItemQty()) {
			message = "삭제";
			CartDeleteItemDto cartDeleteItemDto = modelMapper.map(reqCartItemDto, CartDeleteItemDto.class);
			cartService.deleteCartItem(cartDeleteItemDto);
		} else {
			message = "저장";
			cartService.saveCartItem(reqCartItemDto);
		}

		ResponseResult<CartItemDto> resResult = new ResponseResult<CartItemDto>();
		resResult.setMessage(message+" 되었습니다.");
		resResult.setResult(reqCartItemDto);

        return ResponseEntity.ok(resResult);
	}

	/*
	 * 장바구니 상품 삭제
	 */
	@DeleteMapping(value = "/{itemId}", produces = MediaTypes.HAL_JSON_VALUE)
	public ResponseEntity deleteCartItem(@PathVariable String itemId
			, @RequestBody @Valid CartDeleteItemDto reqCartItemDto
			, @CurrentUser Account currentUser) throws Exception {
		reqCartItemDto.setAccountNo(currentUser.getAccountNo());

		cartService.deleteCartItem(reqCartItemDto);

		ResponseResult<CartDeleteItemDto> resResult = new ResponseResult<CartDeleteItemDto>();
		resResult.setMessage("삭제 되었습니다.");
		resResult.setResult(reqCartItemDto);

        return ResponseEntity.ok(resResult);
	}
}
