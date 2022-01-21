package com.suzyne.shop.domain.event.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class EventSearch {

	private String accountId;
	private String itemId;
	private String itemNm;

	private int startRownum;
	private int page;
	private int listSize;

	public EventSearch(int page, int listSize) {
		this.page = page;
		this.listSize = listSize;
		updatePaging();
	}

	public void updatePaging() {
		this.startRownum = (page - 1) * listSize + 1 ;
	}
}
