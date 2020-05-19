package com.hannamsm.shop.domain.event.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hannamsm.shop.domain.event.dao.EventDao;
import com.hannamsm.shop.domain.event.vo.Event;
import com.hannamsm.shop.domain.event.vo.EventSearch;

@Service
public class EventService {
//	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EventDao eventDao;
	
    public Event create(Event event) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		auth.getName();

		//ID 생성
    	int id = eventDao.selectEventId();
    	event.setId(id);
    	//save
        eventDao.create(event);
        //select saved event 
        Optional<Event> savedEvent = eventDao.findById(event.getId());
        return savedEvent.get();
    }
    
    public Event save(Event event) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		auth.getName();

		event.setLastModPerson(auth.getName());
        eventDao.update(event);

        Optional<Event> savedEvent = eventDao.findById(event.getId());
        return savedEvent.get();
    }
    
    public int findAllTotalCount(EventSearch eventSearch) throws Exception {
    	return eventDao.findAllTotalCount(eventSearch);
    }
    
    public List<Event> findAll(EventSearch eventSearch) throws Exception {
    	return eventDao.findAll(eventSearch);
    }

	public Optional<Event> findById(Integer id) throws Exception {
		return eventDao.findById(id);
	}

}
