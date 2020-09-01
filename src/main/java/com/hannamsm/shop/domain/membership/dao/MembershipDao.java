package com.hannamsm.shop.domain.membership.dao;


import java.util.Optional;

import com.hannamsm.shop.domain.membership.vo.MembershipDto;
import com.hannamsm.shop.global.mapper.SryposConnMapper;

@SryposConnMapper
public interface MembershipDao {
	public MembershipDto findMembership(MembershipDto customer)throws Exception;
}