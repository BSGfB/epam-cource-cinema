package com.epam.cinema.service;

import com.epam.cinema.configuration.annotations.Loggable;
import com.epam.cinema.model.Event;
import com.epam.cinema.model.Ticket;
import com.epam.cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Loggable
@Service
public class BookingServiceImpl implements BookingService {

    private EventService eventService;
    private final TicketService ticketService;

    @Autowired
    public BookingServiceImpl(EventService eventService, TicketService ticketService) {
        this.eventService = eventService;
        this.ticketService = ticketService;
    }


    @Override
    public Double bookTickets(User user, LocalDateTime time, Long eventId, Long seats) {
        Event event = eventService.getById(eventId);

        Double ticketPrice = ticketService.getTicketPrice(event, user, time, seats);

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setDateTime(time);
        ticket.setEvent(event);
        ticket.setSeat(seats);

        user.getTickets().add(ticket);

        ticketService.save(ticket);

        return ticketPrice;
    }


}
