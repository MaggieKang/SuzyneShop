package com.hannamsm.shop.global.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseResutl<T> {

	private String message;

	private T result;
}
