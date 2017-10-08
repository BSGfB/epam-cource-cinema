package com.epam.cinema.service;

import com.epam.cinema.dao.AuditoriumDao;
import com.epam.cinema.dao.EventDao;
import com.epam.cinema.model.Auditorium;
import com.epam.cinema.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

@Service
public class EventServiceImpl implements EventService {

    private EventDao eventDao;
    private AuditoriumDao auditoriumDao;

    @Autowired
    public EventServiceImpl(EventDao eventDao, AuditoriumDao auditoriumDao) {
        this.eventDao = eventDao;
        this.auditoriumDao = auditoriumDao;
    }

    @Override
    public Event addEventAuditorium(Long eventId, Long auditoryId, LocalDateTime localDateTime) {
        notNull(eventId, "eventId must not be null");
        notNull(auditoryId, "auditoryId must not be null");
        notNull(localDateTime, "localDateTime must not be null");
        isTrue(eventId >= 0, "id must be greater than 0");
        isTrue(auditoryId >= 0, "id must be greater than 0");

        Auditorium auditorium = auditoriumDao.getById(auditoryId);
        return eventDao.addAuditorium(eventId, localDateTime, auditorium);
    }

    @Override
    public Event getByName(String name) {
        notNull(name, "name must not be null");

        return eventDao.getByName(name);
    }

    @Override
    public Long save(Event object) {
        notNull(object, "object must not be null");
        return eventDao.save(object);
    }

    @Override
    public void remove(Long id) {
        notNull(id, "id must not be null");
        isTrue(id >= 0, "id must be greater than 0");
        eventDao.remove(id);
    }

    @Override
    public Event getById(Long id) {
        notNull(id, "id must not be null");
        isTrue(id >= 0, "id must be greater than 0");

        return eventDao.getById(id);
    }

    @Override
    public List<Event> getAll() {
        return eventDao.getAll();
    }
}
