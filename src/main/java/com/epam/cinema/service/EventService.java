package com.epam.cinema.service;

import com.epam.cinema.model.Auditorium;
import com.epam.cinema.model.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService extends CrudService<Event> {
    Event getByName(String name);
    Event addEventAuditorium(Long eventId, Long auditoryId, LocalDateTime localDateTime);
    Double getEventPriceByName(String name);
    List<Auditorium> getAuditoriumsByEventId(Long id);
}
