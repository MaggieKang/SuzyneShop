package com.hannamsm.shop.domain.order.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class OrderSearch {
	private int accountNo;
	private String orderId;
	private String storeId;

	@Builder.Default
	private int startRownum = 0;
	@Builder.Default
	private int page = 1;
	@Builder.Default
	private int listSize = 10;

	public OrderSearch(int page, int listSize) {
		this.page = page;
		this.listSize = listSize;
		updatePaging();
	}

	public void updatePaging() {
		this.startRownum = (page - 1) * listSize;
	}
}
