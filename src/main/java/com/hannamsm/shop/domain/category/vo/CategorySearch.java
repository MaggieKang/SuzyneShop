package com.hannamsm.shop.domain.category.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class CategorySearch {
	private String categoryCd;
	private String storeId;
	private String categoryNm;
	private String parentCategoryCd;
	private String parentStoreId;

	private int startRownum;
	private int page;
	private int listSize;

	public CategorySearch(int page, int listSize) {
		this.page = page;
		this.listSize = listSize;
		updatePaging();
	}

	public void updatePaging() {
		this.startRownum = (page - 1) * listSize + 1 ;
	}
}
