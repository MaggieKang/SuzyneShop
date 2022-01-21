package com.suzyne.shop.global.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseResults<T> {

	private String message;

	private List<T> resultList;
}
