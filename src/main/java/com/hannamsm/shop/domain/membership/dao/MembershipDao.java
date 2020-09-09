package com.hannamsm.shop.domain.membership.dao;


import com.hannamsm.shop.domain.membership.vo.MembershipSearchDto;
import com.hannamsm.shop.domain.membership.vo.MembershipDto;
import com.hannamsm.shop.global.mapper.SryposConnMapper;

@SryposConnMapper
public interface MembershipDao {
	public MembershipDto findMembership(MembershipSearchDto reqCustomer)throws Exception;
	
	public int confirmNumber(MembershipSearchDto reqCustomer)throws Exception;
		
	public int findAllCount(MembershipSearchDto reqCustomer)throws Exception;
	
}
