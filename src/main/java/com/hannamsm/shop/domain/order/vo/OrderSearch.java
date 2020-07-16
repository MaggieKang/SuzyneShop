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

	private int startRownum;
	private int page;
	private int listSize;

	public OrderSearch(int page, int listSize) {
		this.page = page;
		this.listSize = listSize;
		updatePaging();
	}

	public void updatePaging() {
		this.startRownum = (page - 1) * listSize;
	}
}
