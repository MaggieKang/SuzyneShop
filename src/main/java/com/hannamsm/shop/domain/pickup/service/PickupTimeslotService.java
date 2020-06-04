package com.hannamsm.shop.domain.pickup.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannamsm.shop.domain.pickup.dao.PickupTimeslotDao;
import com.hannamsm.shop.domain.pickup.vo.PickupTimeslot;
import com.hannamsm.shop.domain.pickup.vo.PickupSlogDtSearch;
import com.hannamsm.shop.domain.pickup.vo.PickupSlotTimeSearch;

@Service
public class PickupTimeslotService {

	@Autowired
	PickupTimeslotDao pickupTimeslotDao;

	public List<PickupTimeslot> findBySlotDt(PickupSlogDtSearch pickupSlogDtSearch) throws Exception {
		return pickupTimeslotDao.findBySlotDt(pickupSlogDtSearch);
	}

	public Optional<PickupTimeslot> findBySlotTime(PickupSlotTimeSearch pickupTimeslotSearch) throws Exception {
		return pickupTimeslotDao.findBySlotTime(pickupTimeslotSearch);
	}

	public int savePickupTimeslot(PickupTimeslot pickupTimeslot) throws Exception {
		return pickupTimeslotDao.saveSlotTime(pickupTimeslot);
	}

	public int deletePickupTimeslot(PickupTimeslot pickupTimeslot) throws Exception {
		return pickupTimeslotDao.deleteTimeslot(pickupTimeslot);
	}

}
