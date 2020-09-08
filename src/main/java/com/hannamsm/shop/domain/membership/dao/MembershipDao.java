package com.hannamsm.shop.domain.membership.dao;


import com.hannamsm.shop.domain.membership.vo.MembershipConfirmDto;
import com.hannamsm.shop.domain.membership.vo.MembershipDto;
import com.hannamsm.shop.global.mapper.SryposConnMapper;

@SryposConnMapper
public interface MembershipDao {
	public MembershipDto findMembership(MembershipDto reqCustomer)throws Exception;
	
	public int confirmNumber(MembershipConfirmDto reqCustomer)throws Exception;
		
	public int findAllCount(MembershipDto reqCustomer)throws Exception;
	
}
