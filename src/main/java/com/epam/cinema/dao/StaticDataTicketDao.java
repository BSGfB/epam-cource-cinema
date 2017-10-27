package com.epam.cinema.dao;

import com.epam.cinema.model.Event;
import com.epam.cinema.model.Ticket;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Profile("static-data")
public class StaticDataTicketDao implements TicketDao {

    private Map<Long, Ticket> tickets = new HashMap<>();

    private static long ID_COUNTER = 0;

    public void setStartValues(List<Ticket> tickets) {
        tickets.forEach(this::save);
    }

    @Override
    public List<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime) {
        return tickets
                .values()
                .stream()
                .filter(ticket -> Objects.equals(ticket.getEvent(), event) && Objects.equals(ticket.getDateTime(), dateTime))
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> getAllByUserId(Long userId) {
        return tickets
                .values()
                .stream()
                .filter(ticket -> Objects.equals(ticket.getUser().getId(), userId))
                .collect(Collectors.toList());
    }

    @Override
    public Long save(Ticket object) {
        object.setId(ID_COUNTER++);
        this.tickets.put(object.getId(), object);
        return object.getId();
    }

    @Override
    public void remove(Long id) {
        this.tickets.remove(id);
    }

    @Override
    public Ticket getById(Long id) {
        return this.tickets.get(id);
    }

    @Override
    public List<Ticket> getAll() {
        return new ArrayList<>(this.tickets.values());
    }
}
