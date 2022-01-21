package com.suzyne.shop.global.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ResponseResultsByPaging<T> {

	// 현재 페이지 번호
	private int page = 1;
	//총 페이지 개수
	private int totalPageCount;

	// 각 페이지 범위 시작 번호 [offset]
	private int startRownum;
	// 페이지당 출력할 데이터 개수 [fetch]
	private int listSize = 50;

	// 조회 총 건수
	private int totalCount;
	// 현재 조회 건수
	private int currentCount;

	private String message;

	private List<T> resultList;

	public ResponseResultsByPaging() {
		super();
		this.page = 0;
		this.listSize = 100;
		// 페이지 시작 번호
		this.startRownum = (page - 1) * listSize + 1 ;
	}

	public ResponseResultsByPaging(int page, int listSize) {
		super();
		this.page = page;
		this.listSize = listSize;
		// 페이지 시작 번호
		this.startRownum = (page - 1) * listSize + 1 ;
	}

	public void update() {
		//전체 페이지수
		this.totalPageCount = (int) Math.ceil((double)totalCount/(double)listSize);
		//페이지 시작 번호
		this.startRownum = (page - 1) * listSize + 1 ;
	}
}
