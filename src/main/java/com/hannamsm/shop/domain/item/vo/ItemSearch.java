package com.hannamsm.shop.domain.item.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ItemSearch {
	private String itemId;
	private String itemName;

	private int startRownum;
	private int page;
	private int listSize;

	public ItemSearch(int page, int listSize) {
		this.page = page;
		this.listSize = listSize;
		updatePaging();
	}

	public void updatePaging() {
		this.startRownum = (page - 1) * listSize + 1 ;
	}
}
