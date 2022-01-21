package com.suzyne.shop.domain.membership.vo;



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
public class MembershipSearchDto {
	@NotEmpty
	private String cardNo;	
	private String phone;
	private String name;
}
