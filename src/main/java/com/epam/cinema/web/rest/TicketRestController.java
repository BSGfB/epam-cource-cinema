package com.epam.cinema.web.rest;

import com.epam.cinema.configuration.annotations.Loggable;
import com.epam.cinema.model.Ticket;
import com.epam.cinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Loggable
@RestController
@RequestMapping(value = "/rest/tickets")
public class TicketRestController {


    private final TicketService ticketService;

    @Autowired
    public TicketRestController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.GET, produces={"application/pdf"})
    public List<Ticket> get(){
        return ticketService.getAll();
    }
}
