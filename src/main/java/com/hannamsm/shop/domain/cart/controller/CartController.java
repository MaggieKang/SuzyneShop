package com.hannamsm.shop.domain.cart.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;

import javax.validation.Valid;

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
import com.hannamsm.shop.domain.cart.vo.CartItem;
import com.hannamsm.shop.domain.cart.vo.CartItemDto;
import com.hannamsm.shop.domain.cart.vo.CartItemSearch;
import com.hannamsm.shop.domain.cart.vo.CartSummery;
import com.hannamsm.shop.global.adapter.CurrentUser;
import com.hannamsm.shop.global.vo.ResponseResutl;
import com.hannamsm.shop.global.vo.ResponseResutlsByPaging;

@RestController
@RequestMapping(value="/api/cart", produces = MediaTypes.HAL_JSON_VALUE)
public class CartController {

	@Autowired
	private CartService cartService;

	/*
	 * 장바구니 목록 조회
	 */
	@GetMapping
	public ResponseEntity queryCartItems(@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "listSize", defaultValue = "100") int listSize,
            @CurrentUser Account account) throws Exception {
		CartItemSearch cartItemSearch = new CartItemSearch(page, listSize);
		cartItemSearch.setAccountId(account.getAccountId());

		int allCount = this.cartService.findByAccountIdCount(cartItemSearch);
		List<CartItem> list = this.cartService.findByAccountId(cartItemSearch);

		//return data
    	ResponseResutlsByPaging<CartItem> resResult = new ResponseResutlsByPaging<CartItem>(page, listSize);
		resResult.setMessage("조회되었습니다.");
		resResult.setTotalCount(allCount);
        resResult.setCurrentCount(list.size());
        resResult.setResultList(list);
        resResult.update();

        return ResponseEntity.ok(resResult);
	}

	//TODO 장바구니 Summary 조회
	@GetMapping("/summery")
	public ResponseEntity queryCartSummery(@CurrentUser Account account) throws Exception {

		ResponseResutl<CartSummery> result = new ResponseResutl<CartSummery>();
		result.setMessage("조회하였습니다.");
		result.setResult(null);
		return ResponseEntity.ok(result);
	}

	/*
	 * 장바구니 상품 추가
	 */
	@PostMapping(value = "/{itemId}", produces = MediaTypes.HAL_JSON_VALUE)
	public ResponseEntity addCartItem(@PathVariable String itemId
			, @RequestBody @Valid CartItemDto reqCartItemDto
			, @CurrentUser Account currentUser) throws Exception {

		reqCartItemDto.setAccountId(currentUser.getAccountId());

		cartService.addCartItem(reqCartItemDto);

		ResponseResutl<CartItemDto> resResult = new ResponseResutl<CartItemDto>();
		resResult.setMessage("추가 되었습니다.");
		resResult.setResult(reqCartItemDto);

        return ResponseEntity
        		.created(linkTo(this.getClass()).slash(itemId).toUri())
        		.body(reqCartItemDto);
	}

	/*
	 * 장바구니 상품 저장
	 */
	@PutMapping(value = "/{itemId}", produces = MediaTypes.HAL_JSON_VALUE)
	public ResponseEntity saveCartItem(@PathVariable String itemId
			, @RequestBody @Valid CartItemDto reqCartItemDto
			, @CurrentUser Account currentUser) throws Exception {
		reqCartItemDto.setAccountId(currentUser.getAccountId());


		cartService.saveCartItem(reqCartItemDto);

		ResponseResutl<CartItemDto> resResult = new ResponseResutl<CartItemDto>();
		resResult.setMessage("저장 되었습니다.");
		resResult.setResult(reqCartItemDto);

        return ResponseEntity.ok(reqCartItemDto);
	}

	/*
	 * 장바구니 상품 삭제
	 */
	@DeleteMapping(value = "/{itemId}", produces = MediaTypes.HAL_JSON_VALUE)
	public ResponseEntity deleteCartItem(@PathVariable String itemId
			, @RequestBody @Valid CartItemDto reqCartItemDto
			, @CurrentUser Account currentUser) throws Exception {
		reqCartItemDto.setAccountId(currentUser.getAccountId());


		cartService.deleteCartItem(reqCartItemDto);

		ResponseResutl<CartItemDto> resResult = new ResponseResutl<CartItemDto>();
		resResult.setMessage("삭제 되었습니다.");
		resResult.setResult(reqCartItemDto);

        return ResponseEntity.ok(reqCartItemDto);
	}


}
