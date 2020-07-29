package com.hannamsm.shop.domain.membership.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannamsm.shop.domain.membership.dao.MembershipDao;
import com.hannamsm.shop.domain.membership.vo.MembershipDto;
import com.hannamsm.shop.domain.account.exception.AccountIdNotFoundException;


@Service
public class MembershipService {
	@Autowired
	MembershipDao membershipDao;
	//find membership
    public Optional<MembershipDto> findMembership(MembershipDto member) throws Exception{
    	Optional<MembershipDto> returnStr = membershipDao.findMembership(member);
    	returnStr.orElseThrow(() -> new AccountIdNotFoundException());
    	return returnStr; 
    }
}
