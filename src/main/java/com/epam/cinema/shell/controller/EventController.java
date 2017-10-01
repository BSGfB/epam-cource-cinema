package com.epam.cinema.shell.controller;

import com.epam.cinema.model.Event;
import com.epam.cinema.model.EventRating;
import com.epam.cinema.service.EventService;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class EventController implements CommandMarker {

    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @CliCommand(value = {"events"})
    public String getEvents(@CliOption(key = "all", mandatory = false) String all,
                           @CliOption(key = "id", mandatory = false) Long id,
                           @CliOption(key = "name", mandatory = false) String name) {
        if (!isNull(id))
            return eventService.getById(id).toString();
        if (!isNull(name))
            return eventService.getByName(name).toString();

        return eventService.getAll().stream().map(Event::toString).collect(Collectors.joining("\n"));
    }

    @CliCommand(value = {"rm-event"})
    public void removeEvents(@CliOption(key = "id", mandatory = false) Long id) {
        eventService.remove(id);
    }


    @CliCommand(value = {"create-event"})
    public String createEvent(@CliOption(key = "name", mandatory = true) String name,
                             @CliOption(key = "rating", mandatory = true) EventRating rating,
                             @CliOption(key = "basePrice", mandatory = true) Double basePrice) {
        Event event = new Event();
        event.setName(name);
        event.setRating(rating);
        event.setBasePrice(basePrice);

        return "Added new event with id: " + eventService.save(event);
    }

    @CliCommand(value = {"add-event-auditorium"})
    public String addEventAuditory(@CliOption(key = "eventId", mandatory = true) Long eventId,
                                   @CliOption(key = "auditoriumId", mandatory = true) Long auditoriumId,
                                   @CliOption(key = "time", mandatory = true) LocalDateTime time) {
        return eventService.addEventAuditorium(eventId, auditoriumId, time).toString();
    }
}
