package com.suzyne.shop.domain.membership.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suzyne.shop.domain.membership.dao.MembershipDao;
import com.suzyne.shop.domain.membership.dao.ShopMembershipDao;
import com.suzyne.shop.domain.membership.vo.MembershipSearchDto;
import com.suzyne.shop.domain.profile.vo.AddressDto;
import com.suzyne.shop.domain.profile.vo.ProfileDto;
import com.suzyne.shop.domain.membership.vo.MembershipDto;


@Service
public class MembershipService {
	@Autowired
	MembershipDao membershipDao;
	@Autowired
	ShopMembershipDao shopMembershipDao;
	//find membership
    public MembershipDto findMembership(MembershipSearchDto reqCustomer) throws Exception{    	    	
    	return membershipDao.findMembership(reqCustomer); 
    }
    public int confirmNumber(MembershipSearchDto reqCustomer) throws Exception{
    	return membershipDao.confirmNumber(reqCustomer);
    }
    public int findAllCount(MembershipSearchDto reqCustomer) throws Exception{
    	return membershipDao.findAllCount(reqCustomer);
    }
    public MembershipDto searchMembership(MembershipSearchDto reqCustomer) throws Exception{
    	return membershipDao.searchMembership(reqCustomer);
    }
    public int saveMemProfile(ProfileDto profileDto) throws Exception{
    	return shopMembershipDao.saveMemProfile(profileDto);
    }
    public int saveMemAddress(AddressDto addressDto)throws Exception{
    	return shopMembershipDao.saveMemAddress(addressDto);
    }
	public Integer findAddressSeq(int accountNo) throws Exception{
		return shopMembershipDao.findAddressSeq(accountNo);
	}
	public String queryMembershipId(int accountNo) throws Exception{
		return shopMembershipDao.queryMembershipId(accountNo);
	}
    public int membershipDupCheck(String membershipId) throws Exception{
    	return shopMembershipDao.membershipDupCheck(membershipId);
    }
}
