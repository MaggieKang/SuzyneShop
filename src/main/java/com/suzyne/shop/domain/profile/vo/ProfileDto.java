package com.suzyne.shop.domain.profile.vo;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class ProfileDto {	
	private int accountNo;			
	private String firstName;
	private String lastName;
	private String membershipId;
	private String customerEmail;
	private String customerPhoneNumber;
	private String extensionNumber;
}
