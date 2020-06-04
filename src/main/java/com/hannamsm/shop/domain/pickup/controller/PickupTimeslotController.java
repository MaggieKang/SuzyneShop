package com.hannamsm.shop.domain.pickup.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.domain.pickup.service.PickupTimeslotService;
import com.hannamsm.shop.domain.pickup.vo.PickupTimeslot;
import com.hannamsm.shop.domain.pickup.vo.PickupSlogDtSearch;
import com.hannamsm.shop.domain.pickup.vo.PickupSlotTimeSearch;
import com.hannamsm.shop.global.vo.ResponseResutl;
import com.hannamsm.shop.global.vo.ResponseResutlsByPaging;

@RestController
@RequestMapping(value="/api/pickup", produces = MediaTypes.HAL_JSON_VALUE)
public class PickupTimeslotController {

	@Autowired
	private PickupTimeslotService pickupTimeslotService;

	/*
	 * 픽업 날짜 시간표 조회
	 */
	@GetMapping(value = "/slotdate")
	public ResponseEntity getPickupSlogDt(@RequestBody @Valid PickupSlogDtSearch pickupSlogDtSearch) throws Exception {

		List<PickupTimeslot> list = pickupTimeslotService.findBySlotDt(pickupSlogDtSearch);

		//return data
    	ResponseResutlsByPaging<PickupTimeslot> resResult = new ResponseResutlsByPaging<PickupTimeslot>();
		resResult.setMessage("조회되었습니다.");
		resResult.setTotalCount(list.size());
        resResult.setCurrentCount(list.size());
        resResult.setResultList(list);
        resResult.update();

        return ResponseEntity.ok(resResult);
	}

	/*
	 * 픽업 시간 조회
	 */
	@GetMapping(value = "/slottime")
	public ResponseEntity getPickupSlotTime(@RequestBody @Valid PickupSlotTimeSearch pickupSlotTimeSearch) throws Exception {

		Optional<PickupTimeslot> optionalResult = pickupTimeslotService.findBySlotTime(pickupSlotTimeSearch);
		if(optionalResult.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		//return data
		ResponseResutl<PickupTimeslot> result = new ResponseResutl<PickupTimeslot>();
		result.setMessage("조회하였습니다.");
		result.setResult(optionalResult.get());
		return ResponseEntity.ok(result);
	}

}
