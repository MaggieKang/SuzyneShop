package com.suzyne.shop.global.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseResult<T> {

	private String message;

	private T result;
}
