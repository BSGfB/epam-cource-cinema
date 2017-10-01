package com.epam.cinema.dao;

import com.epam.cinema.model.Auditorium;
import com.epam.cinema.model.Event;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaticDataEventDao implements EventDao {

    private static Log logger = LogFactory.getLog(StaticDataEventDao.class);


    private Map<Long, Event> events = new HashMap<>();

    private static long ID_COUNTER = 0;

    public void setStartValues(List<Event> events) {
        logger.debug("setStartValues: " + events);
        events.forEach(this::save);
    }

    @Override
    public Event getByName(String name) {
        logger.debug("getByName: " + name);

        return this.events.values().stream().filter(event -> event.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Event addAuditorium(Long id, LocalDateTime localDateTime, Auditorium auditorium) {
        logger.debug("addAuditorium: id" + id + " localDateTime: " + localDateTime + " auditorium: " + auditorium);

        Event event = events.get(id);
        event.getAuditoriums().put(localDateTime, auditorium);
        return event;
    }

    @Override
    public Long save(Event object) {
        logger.debug("save: " + object);

        object.setId(ID_COUNTER++);
        this.events.put(object.getId(), object);
        return object.getId();
    }

    @Override
    public void remove(Long id) {
        logger.debug("remove: " + id);
        this.events.remove(id);
    }

    @Override
    public Event getById(Long id) {
        logger.debug("getById: " + id);

        return this.events.get(id);
    }

    @Override
    public List<Event> getAll() {
        logger.debug("getAll");

        return new ArrayList<>(this.events.values());
    }


}
