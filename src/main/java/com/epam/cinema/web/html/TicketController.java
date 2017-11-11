package com.epam.cinema.web.html;

import com.epam.cinema.model.Event;
import com.epam.cinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @Secured("MANAGER")
    @RequestMapping(value = {"/all"}, method = RequestMethod.GET)
    public ModelAndView getAll() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("tickets", ticketService.getAll());

        return mav;
    }

    @RequestMapping(value = "/byId", method = RequestMethod.GET)
    public ModelAndView getById(@RequestParam(value = "id") Long id) {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("ticket", ticketService.getById(id));

        return mav;
    }

    @RequestMapping(value = "/byUserId", method = RequestMethod.GET)
    public ModelAndView getByUserId(@RequestParam(value = "id") Long id) {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("tickets", ticketService.getAllByUserId(id));

        return mav;
    }

    @RequestMapping(value = "/byEventId", method = RequestMethod.GET)
    public ModelAndView getByEventId(@RequestParam(value = "eventId") Long id) {
        ModelAndView mav = new ModelAndView("index");
        Event event = new Event();
        event.setId(id);

        mav.addObject("tickets", ticketService.getPurchasedTicketsForEvent(event, LocalDateTime.now()));

        return mav;
    }

    @RequestMapping(value = "/byUserIdAsPdf", method = RequestMethod.GET, produces={"application/pdf"})
    public ModelAndView getByUserIdAsPdf(@RequestParam(value = "id") Long id) {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("tickets", ticketService.getAllByUserId(id));

        return mav;
    }

    @RequestMapping(value = "/byUserId", method = RequestMethod.GET, produces={"application/pdf"})
    public ModelAndView getByUserIdPdf(@RequestParam(value = "id") Long id) {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("tickets", ticketService.getAllByUserId(id));

        return mav;
    }
}
