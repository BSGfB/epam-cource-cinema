package com.epam.cinema.web.html;

import com.epam.cinema.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    EventService eventService;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public ModelAndView getAll() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("events", eventService.getAll());

        return mav;
    }

    @RequestMapping(value = "/byId", method = RequestMethod.GET)
    public ModelAndView getById(@RequestParam(value = "id") Long id) {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("event", eventService.getById(id));

        return mav;
    }
}
