package com.hannamsm.shop.domain.account.vo;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @EqualsAndHashCode(of = "seq")
@Builder @NoArgsConstructor @AllArgsConstructor
@ToString
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer seq;
    private String id;
    private String name;
    private String password;
    private String email;
    //계정이 만료되었는지
    private boolean isUseAccountNonExpired;
    //계정이 잠겼는지(패스워드를 몇회 틀려서)
    private boolean isAccountNonLocked;
    //패스워드가 만료되었는지(몇 개월 주기로 변경)
    private boolean isCredentialsNonExpired;
    //계정 활성화 여부
    private boolean isEnabled;
	
    private List<String> roles;
}
