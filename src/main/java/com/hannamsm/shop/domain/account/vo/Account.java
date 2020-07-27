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

@Getter @Setter @EqualsAndHashCode(of = "accountNo")
@Builder @NoArgsConstructor @AllArgsConstructor
@ToString
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

    private int accountNo;
    private String accountEmail;
    private String password;
    private String newPassword;
    //계정이 만료되었는지
    private boolean isExpired;
    //계정이 잠겼는지(패스워드를 몇회 틀려서)
    private boolean isLocked;
    //계정 활성화 여부
    private boolean isUse;

    private List<String> roles;
}
