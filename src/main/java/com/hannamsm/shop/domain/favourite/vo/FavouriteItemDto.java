package com.hannamsm.shop.domain.favourite.vo;

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
public class FavouriteItemDto {

	private String accountId;
	private String itemId;
	private LocalDateTime regDate;
	private String regPerson;
	private LocalDateTime lastModDate;
	private String lastModPerson;
}
