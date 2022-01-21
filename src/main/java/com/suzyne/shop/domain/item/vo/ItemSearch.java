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
public class ItemSearch {
	private String storeId;
	private String itemId;
	private String itemName;
	private String itemCategory;
	private String itemSort;

	private int startRownum;
	private int page;
	private int listSize;

	public ItemSearch(int page, int listSize) {
		this.page = page;
		this.listSize = listSize;
		updatePaging();
	}

	public void updatePaging() {
		this.startRownum = (page - 1) * listSize;
	}
}
