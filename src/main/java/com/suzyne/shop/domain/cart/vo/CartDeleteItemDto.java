package com.suzyne.shop.domain.cart.vo;

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
public class CartDeleteItemDto {
	private int accountNo;
	@NotEmpty
	private String storeId;
	@NotEmpty
	private String itemId;
	@NotEmpty
	private String itemSalesTypeCd;
}
