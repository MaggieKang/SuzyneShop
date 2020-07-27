package com.hannamsm.shop.domain.event.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.domain.account.vo.Account;
import com.hannamsm.shop.domain.event.service.EventService;
import com.hannamsm.shop.domain.event.validator.EventValidator;
import com.hannamsm.shop.domain.event.vo.Event;
import com.hannamsm.shop.domain.event.vo.EventDto;
import com.hannamsm.shop.domain.event.vo.EventSearch;
import com.hannamsm.shop.domain.event.vo.ResEventDto;
import com.hannamsm.shop.global.adapter.CurrentUser;
import com.hannamsm.shop.global.vo.ResponseResult;
import com.hannamsm.shop.global.vo.ResponseResutlsByPaging;

@RestController
@RequestMapping(value="/api/events", produces = MediaTypes.HAL_JSON_VALUE)
public class EventController {
//	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EventService eventService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private EventValidator eventValidator;

	@PostMapping
	public ResponseEntity createEvent(@RequestBody @Valid EventDto reqEventDto
			, @CurrentUser Account account) throws Exception {

		this.eventValidator.validate(reqEventDto);

		Event event = modelMapper.map(reqEventDto, Event.class);
		event.update();
		event.setRegPerson(String.valueOf(account.getAccountNo()));
		event.setLastModPerson(String.valueOf(account.getAccountNo()));

		Event savedEvent = this.eventService.create(event);

		ResEventDto resEventDto = modelMapper.map(savedEvent, ResEventDto.class);

		ResponseResult<ResEventDto> resResult = new ResponseResult<ResEventDto>();
		resResult.setMessage("생성되었습니다.");
		resResult.setResult(resEventDto);

        return ResponseEntity
        		.created(linkTo(this.getClass()).slash(event.getId()).toUri())
        		.body(resEventDto);
	}

	@GetMapping
	public ResponseEntity queryEvents(@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "listSize", defaultValue = "100") int listSize) throws Exception {
		EventSearch eventSearch = new EventSearch(page, listSize);

		//get data
		int totalCount = this.eventService.findAllTotalCount(eventSearch);
		List<Event> list = this.eventService.findAll(eventSearch);

		//return data
    	ResponseResutlsByPaging<Event> resResult = new ResponseResutlsByPaging<Event>(page, listSize);
		resResult.setMessage("조회되었습니다.");
		resResult.setTotalCount(totalCount);
        resResult.setCurrentCount(list.size());
        resResult.setResultList(list);
        resResult.update();

        return ResponseEntity.ok(resResult);
	}

	@GetMapping("/{id}")
	public ResponseEntity getEvent(@PathVariable Integer id) throws Exception {
		Optional<Event> optionalEvent= this.eventService.findById(id);
		if(optionalEvent.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		ResponseResult<Event> result = new ResponseResult<Event>();
		result.setMessage("조회하였습니다.");
		result.setResult(optionalEvent.get());
		return ResponseEntity.ok(result);
	}

	@PutMapping("/{id}")
	public ResponseEntity updateEvent(@PathVariable Integer id, @RequestBody @Valid EventDto eventDto) throws Exception {
		Optional<Event> optionalEvent = this.eventService.findById(id);
		if(optionalEvent.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		this.eventValidator.validate(eventDto);

		Event existingEvent = optionalEvent.get();
		this.modelMapper.map(eventDto, existingEvent);
		Event savedEvent = this.eventService.save(existingEvent);

		ResEventDto resEventDto = modelMapper.map(savedEvent, ResEventDto.class);

		ResponseResult<ResEventDto> resResult = new ResponseResult<ResEventDto>();
		resResult.setMessage("변경되었습니다.");
		resResult.setResult(resEventDto);

		return ResponseEntity.ok(resResult);
	}
}
