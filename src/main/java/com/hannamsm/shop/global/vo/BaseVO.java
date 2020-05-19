package com.hannamsm.shop.global.vo;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BaseVO {
	protected LocalDateTime regDate;
	protected String regPerson;
	protected LocalDateTime lastModDate;
	protected String lastModPerson;
}
