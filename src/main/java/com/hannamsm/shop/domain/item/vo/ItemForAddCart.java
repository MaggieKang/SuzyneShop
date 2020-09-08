package com.hannamsm.shop.domain.item.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class ItemForAddCart {
	private String storeId;

	/* 상품ID */
	private String itemId;
	private String itemSalesTypeCd;
	private int addSalesQty;
}
