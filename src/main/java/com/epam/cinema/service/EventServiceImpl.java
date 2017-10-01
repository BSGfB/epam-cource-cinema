package com.epam.cinema.service;

import com.epam.cinema.dao.AuditoriumDao;
import com.epam.cinema.dao.EventDao;
import com.epam.cinema.model.Auditorium;
import com.epam.cinema.model.Event;

import java.time.LocalDateTime;
import java.util.List;

public class EventServiceImpl implements EventService {

    private EventDao eventDao;
    private AuditoriumDao auditoriumDao;

    public EventServiceImpl(EventDao eventDao, AuditoriumDao auditoriumDao) {
        this.eventDao = eventDao;
        this.auditoriumDao = auditoriumDao;
    }

    @Override
    public Event addEventAuditorium(Long eventId, Long auditoryId, LocalDateTime localDateTime) {
        Auditorium auditorium = auditoriumDao.getById(auditoryId);
        return eventDao.addAuditorium(eventId, localDateTime, auditorium);
    }

    @Override
    public Event getByName(String name) {
        return eventDao.getByName(name);
    }

    @Override
    public Long save(Event object) {
        return eventDao.save(object);
    }

    @Override
    public void remove(Long id) {
        eventDao.remove(id);
    }

    @Override
    public Event getById(Long id) {
        return eventDao.getById(id);
    }

    @Override
    public List<Event> getAll() {
        return eventDao.getAll();
    }
}
