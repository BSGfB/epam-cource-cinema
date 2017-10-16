package com.epam.cinema.shell.controller;

import com.epam.cinema.model.Ticket;
import com.epam.cinema.service.AuthorizationService;
import com.epam.cinema.service.BookingService;
import com.epam.cinema.service.TicketService;
import org.springframework.context.annotation.Profile;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static java.util.Objects.isNull;

@Profile("spring-shell")
@Component
public class BookingController implements CommandMarker {
    private AuthorizationService authorizationService;
    private BookingService bookingService;
    private TicketService ticketService;

    public BookingController(AuthorizationService authorizationService, BookingService bookingService, TicketService ticketService) {
        this.authorizationService = authorizationService;
        this.bookingService = bookingService;
        this.ticketService = ticketService;
    }

    /**
     * Request: book-ticket --eventId 0 --time 2017-10-01T18:30:00 --seats 15
     * @param id event id
     * @param time event time: text string such as 2007-12-03T10:15:30
     * @param seats Amount booking seats
     * @return Result of booking
     */
    @CliCommand(value = {"book-ticket"})
    public String bookTicket(@CliOption(key = "eventId", mandatory = true) Long id,
                             @CliOption(key = "time", mandatory = true) LocalDateTime time,
                             @CliOption(key = "seats", mandatory = true) Long seats) {
        if (authorizationService.isAuthorized()) {
            return "Ticket coast is " + bookingService.bookTickets(authorizationService.getUser(), time, id, seats) + "$";
        }

        return "Please sign in. Thanks!";
    }

    @CliCommand(value = {"rm-ticket"})
    public String removeTicket(@CliOption(key = "id", mandatory = false) Long id) {
        if (authorizationService.isAuthorized()) {
            Ticket ticket = ticketService.getById(id);
            if (!isNull(ticket)) {
                authorizationService.getUser().getTickets().remove(ticket);
                ticketService.remove(id);
                return "Ticket removed";
            }
            else
                return "Wrong id";
        }

        return "Please sign in. Thanks!";
    }

}
