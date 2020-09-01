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
    private int cardKey;
    private String cardNo;
    private String webID;
    private String lang_cd;
    private String storeCode;
    private String custLanguage;
    private String sex;
    private String enName;
    private String phone;
    private String address;
    private String city_cd;
    private String city;
    private String province_cd;
    private String province;
    private String country_cd;
    private String postalCode;
    private String email;
    private String krName;
    private String cardIssueDate;
    private String lastModDate;
}
