package com.epam.cinema.service;

import com.epam.cinema.dao.TicketDao;
import com.epam.cinema.model.Event;
import com.epam.cinema.model.Ticket;
import com.epam.cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private TicketDao ticketDao;
    private DiscountService discountService;
    private EventService eventService;

    @Autowired
    public BookingServiceImpl(TicketDao ticketDao, DiscountService discountService, EventService eventService) {
        this.ticketDao = ticketDao;
        this.discountService = discountService;
        this.eventService = eventService;
    }

    @Override
    public Long save(Ticket object) {
        return ticketDao.save(object);
    }

    @Override
    public void remove(Long id) {
        ticketDao.remove(id);
    }

    @Override
    public Ticket getById(Long id) {
        return ticketDao.getById(id);
    }

    @Override
    public List<Ticket> getAll() {
        return ticketDao.getAll();
    }

    @Override
    public Double getTicketPrice(Event event,User user, LocalDateTime dateTime, Long seat) {
        return event.getBasePrice() * seat - discountService.calculateDiscount(event, user, dateTime, seat);
    }

    @Override
    public List<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime) {
        return ticketDao.getPurchasedTicketsForEvent(event, dateTime);
    }

    @Override
    public Double bookTickets(User user, LocalDateTime time, Long eventId, Long seats) {
        Event event = eventService.getById(eventId);

        Double ticketPrice = this.getTicketPrice(event, user, time, seats);

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setDateTime(time);
        ticket.setEvent(event);
        ticket.setSeat(seats);

        user.getTickets().add(ticket);

        ticketDao.save(ticket);

        return ticketPrice;
    }
}
