package com.epam.cinema.service;

import com.epam.cinema.model.Event;

import java.time.LocalDateTime;

public interface EventService extends CrudService<Event> {
    Event getByName(String name);
    Event addEventAuditorium(Long eventId, Long auditoryId, LocalDateTime localDateTime);
}
