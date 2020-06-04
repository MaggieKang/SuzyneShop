package com.hannamsm.shop.domain.pickup.dao;

import java.util.List;
import java.util.Optional;

import com.hannamsm.shop.domain.pickup.vo.PickupTimeslot;
import com.hannamsm.shop.domain.pickup.vo.PickupSlogDtSearch;
import com.hannamsm.shop.domain.pickup.vo.PickupSlotTimeSearch;

public interface PickupTimeslotDao {

	public List<PickupTimeslot> findBySlotDt(PickupSlogDtSearch pickupSlogDtSearch) throws Exception;

	public Optional<PickupTimeslot> findBySlotTime(PickupSlotTimeSearch pickupTimeslotSearch) throws Exception;

	public int saveSlotTime(PickupTimeslot pickupTimeslot) throws Exception;

	public int deleteTimeslot(PickupTimeslot pickupTimeslot) throws Exception;
}
