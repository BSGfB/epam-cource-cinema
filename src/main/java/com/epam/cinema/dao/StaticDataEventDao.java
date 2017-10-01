package com.epam.cinema.dao;

import com.epam.cinema.model.Auditorium;
import com.epam.cinema.model.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaticDataEventDao implements EventDao {

    private Map<Long, Event> events = new HashMap<>();

    private static long ID_COUNTER = 0;

    public void setStartValues(List<Event> events) {
        events.forEach(this::save);
    }

    @Override
    public Event getByName(String name) {
        return this.events.values().stream().filter(event -> event.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Event addAuditorium(Long id, LocalDateTime localDateTime, Auditorium auditorium) {
        Event event = events.get(id);
        event.getAuditoriums().put(localDateTime, auditorium);
        return event;
    }

    @Override
    public Long save(Event object) {
        object.setId(ID_COUNTER++);
        this.events.put(object.getId(), object);
        return object.getId();
    }

    @Override
    public void remove(Long id) {
        this.events.remove(id);
    }

    @Override
    public Event getById(Long id) {
        return this.events.get(id);
    }

    @Override
    public List<Event> getAll() {
        return new ArrayList<>(this.events.values());
    }


}
