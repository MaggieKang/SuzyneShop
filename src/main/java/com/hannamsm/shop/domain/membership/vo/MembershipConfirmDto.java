package com.hannamsm.shop.domain.membership.vo;



import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class MembershipConfirmDto {
	@NotEmpty
	private String cardNo;
	@NotEmpty
	private String phone;
	private int result;
}
