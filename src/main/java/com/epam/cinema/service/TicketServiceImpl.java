package com.epam.cinema.service;

import com.epam.cinema.dao.TicketDao;
import com.epam.cinema.model.Event;
import com.epam.cinema.model.Ticket;
import com.epam.cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class TicketServiceImpl implements TicketService {

    private final TicketDao ticketDao;
    private final DiscountService discountService;

    @Autowired
    public TicketServiceImpl(TicketDao ticketDao, DiscountService discountService) {
        this.ticketDao = ticketDao;
        this.discountService = discountService;
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

}
