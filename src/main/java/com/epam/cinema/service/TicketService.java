package com.epam.cinema.service;

import com.epam.cinema.dao.CrudDao;
import com.epam.cinema.model.Event;
import com.epam.cinema.model.Ticket;
import com.epam.cinema.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketService extends CrudDao<Ticket> {
    Double getTicketPrice(Event event, User user, LocalDateTime dateTime, Long seat);
    List<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime);
}
