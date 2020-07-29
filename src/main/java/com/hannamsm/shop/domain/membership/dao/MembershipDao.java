package com.hannamsm.shop.domain.membership.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;

import com.hannamsm.shop.domain.membership.vo.MembershipDto;
import com.hannamsm.shop.global.mapper.SryposConnMapper;

@SryposConnMapper
public interface MembershipDao {
	public Optional<MembershipDto> findMembership(MembershipDto customer)throws Exception;
}