package com.epam.cinema.service;

import com.epam.cinema.configuration.annotations.Loggable;
import com.epam.cinema.model.Event;
import com.epam.cinema.model.Ticket;
import com.epam.cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Loggable
@Service
public class BookingServiceImpl implements BookingService {

    private EventService eventService;
    private final TicketService ticketService;
    private final UserAccountService userAccountService;

    @Autowired
    public BookingServiceImpl(EventService eventService, TicketService ticketService, UserAccountService userAccountService) {
        this.eventService = eventService;
        this.ticketService = ticketService;
        this.userAccountService = userAccountService;
    }


    @Override
    @Transactional
    public Double bookTickets(User user, LocalDateTime time, Long eventId, Long seats) throws Exception {
        Event event = eventService.getById(eventId);

        Double ticketPrice = ticketService.getTicketPrice(event, user, time, seats);
        Ticket ticket = new Ticket(user, event, time, seats);

        int result = userAccountService.removeMoney(user.getId(), new BigDecimal(ticketPrice));

        if (result == 1)
            ticketService.save(ticket);
        else
            throw new Exception("User does not have enough money to buy a ticket");

        user.getTickets().add(ticket);

        return ticketPrice;
    }
}
