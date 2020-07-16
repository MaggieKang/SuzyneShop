package com.hannamsm.shop.domain.category.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class Category {

	private String categoryCd;
	private String categoryNm;
	private String categoryDesc;
	private String parentCategoryCd;
	private boolean isUse;
	private LocalDateTime regDate;
	private String regPerson;
	private LocalDateTime lastModDate;
	private String lastModPerson;
}
