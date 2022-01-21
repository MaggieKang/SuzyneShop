package com.suzyne.shop.domain.membership.dao;


import com.suzyne.shop.domain.membership.vo.MembershipSearchDto;
import com.suzyne.shop.domain.membership.vo.MembershipDto;
import com.suzyne.shop.global.mapper.SryposConnMapper;

@SryposConnMapper
public interface MembershipDao {
	public MembershipDto findMembership(MembershipSearchDto reqCustomer)throws Exception;
	
	public int confirmNumber(MembershipSearchDto reqCustomer)throws Exception;
		
	public int findAllCount(MembershipSearchDto reqCustomer)throws Exception;
	
	public MembershipDto searchMembership(MembershipSearchDto reqCustomer)throws Exception;
	
}
