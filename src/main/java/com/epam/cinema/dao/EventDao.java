package com.epam.cinema.dao;

import com.epam.cinema.model.Auditorium;
import com.epam.cinema.model.Event;

import java.time.LocalDateTime;

public interface EventDao extends CrudDao<Event> {
    Event getByName(String name);
    Event addAuditorium(Long id, LocalDateTime localDateTime, Auditorium auditorium);
}