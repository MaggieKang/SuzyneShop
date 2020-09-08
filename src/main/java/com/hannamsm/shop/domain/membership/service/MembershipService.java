package com.hannamsm.shop.domain.membership.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannamsm.shop.domain.membership.dao.MembershipDao;
import com.hannamsm.shop.domain.membership.vo.MembershipConfirmDto;
import com.hannamsm.shop.domain.membership.vo.MembershipDto;
import com.hannamsm.shop.domain.account.exception.AccountIdNotFoundException;


@Service
public class MembershipService {
	@Autowired
	MembershipDao membershipDao;
	//find membership
    public MembershipDto findMembership(MembershipDto reqCustomer) throws Exception{    	    	
    	return membershipDao.findMembership(reqCustomer); 
    }
    public int confirmNumber(MembershipConfirmDto reqCustomer) throws Exception{
    	return membershipDao.confirmNumber(reqCustomer);
    }
    public int findAllCount(MembershipDto reqCustomer) throws Exception{
    	return membershipDao.findAllCount(reqCustomer);
    }
    
}
