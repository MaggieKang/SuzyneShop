package com.hannamsm.shop.domain.cart.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class CartItemDto {
	private String accountId;
	@NotEmpty
	private String storeId;
	@NotEmpty
	private String itemId;
	@Min(0)
	private int itemQty;
}
