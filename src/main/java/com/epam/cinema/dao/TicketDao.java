package com.epam.cinema.dao;

import com.epam.cinema.model.Event;
import com.epam.cinema.model.Ticket;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketDao extends CrudDao <Ticket> {
    List<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime);
}
