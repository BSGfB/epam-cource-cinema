package com.epam.cinema.dao;

import com.epam.cinema.model.Auditorium;
import com.epam.cinema.model.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventDao extends CrudDao<Event> {
    Event getByName(String name);
    Event addAuditorium(Long id, LocalDateTime localDateTime, Auditorium auditorium);
    List<Auditorium> getAuditoriumsByEventId(Long id);
}