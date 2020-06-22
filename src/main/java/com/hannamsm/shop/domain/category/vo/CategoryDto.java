package com.hannamsm.shop.domain.category.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class CategoryDto {

	private String categoryCd;
	private String categoryNm;
	private String categoryDesc;
	private String parentCategoryCd;
	private List<CategoryDto> subCategoryList;
	private boolean isUse;
}
