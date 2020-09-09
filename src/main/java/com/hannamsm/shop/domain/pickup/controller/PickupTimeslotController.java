package com.hannamsm.shop.domain.pickup.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.domain.pickup.service.PickupTimeslotService;
import com.hannamsm.shop.domain.pickup.vo.PickupSlogDtDefaultSearch;
import com.hannamsm.shop.domain.pickup.vo.PickupSlogDtSearch;
import com.hannamsm.shop.domain.pickup.vo.PickupSlotTimeSearch;
import com.hannamsm.shop.domain.pickup.vo.PickupTimeslot;
import com.hannamsm.shop.domain.pickup.vo.PickupTimeslotDefault;
import com.hannamsm.shop.global.utils.DateUtil;
import com.hannamsm.shop.global.vo.ResponseResult;
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
	public ResponseEntity getPickupSlogDt(
			@RequestParam(value = "storeId") String storeId,
			@RequestParam(value = "slotDt") String slotDt) throws Exception {

		PickupSlogDtSearch pickupSlogDtSearch = PickupSlogDtSearch.builder()
			.slotDt(slotDt)
			.storeId(storeId)
			.build();

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
	 * 픽업 날짜 기본 시간표 조회
	 */
	@GetMapping(value = "/default/slotdate")
	public ResponseEntity getPickupSlogDtDefault(
            @RequestParam(value = "storeId") String storeId,
            @RequestParam(value = "slotDt") String slotDt) throws Exception {

		PickupSlogDtDefaultSearch pickupSlogDtDefaultSearch = PickupSlogDtDefaultSearch.builder()
				.slotDt(slotDt)
				.storeId(storeId)
				.defaultDayWeek((new DateUtil()).getDayOfWeek(slotDt, "yyyy-MM-dd"))
				.build();

		List<PickupTimeslotDefault> list = pickupTimeslotService.findBySlotDtDefault(pickupSlogDtDefaultSearch);

		//return data
    	ResponseResutlsByPaging<PickupTimeslotDefault> resResult = new ResponseResutlsByPaging<PickupTimeslotDefault>();
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
		ResponseResult<PickupTimeslot> result = new ResponseResult<PickupTimeslot>();
		result.setMessage("조회하였습니다.");
		result.setResult(optionalResult.get());
		return ResponseEntity.ok(result);
	}

}
