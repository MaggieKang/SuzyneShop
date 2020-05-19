package com.hannamsm.shop.global.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseResutls<T> {

	private String message;

	private List<T> resultList;
}
