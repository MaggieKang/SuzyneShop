package com.hannamsm.shop.global.email;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmailDto {
	  private String address;
	  private String title;
	  private String message;
}