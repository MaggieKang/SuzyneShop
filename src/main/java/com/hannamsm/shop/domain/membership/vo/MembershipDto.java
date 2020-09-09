package com.hannamsm.shop.domain.membership.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class MembershipDto {
    
    private String cardNo;    
    private String enName;
    private String phone;
    private String address;    
    private String city;    
    private String province;    
    private String postalCode;    
    private String krName;  
    private int result;
}
