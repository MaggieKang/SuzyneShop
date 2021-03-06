package com.suzyne.shop.domain.item.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class ItemForAddCartSearch {
	private int accountNo;
	private String storeId;
	private String itemId;
}
