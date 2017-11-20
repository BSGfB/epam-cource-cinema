package com.epam.cinema.web.rest;

import com.epam.cinema.configuration.annotations.Loggable;
import com.epam.cinema.model.Event;
import com.epam.cinema.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Loggable
@RestController
@RequestMapping(value = "/rest/events")
public class EventRestController {

    private final EventService eventService;

    @Autowired
    public EventRestController(EventService eventService) {
        this.eventService = eventService;
    }

    @ResponseBody
    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    public Event getById(@RequestParam("id") Long id){
        return eventService.getById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public List<Event> getUser(){
        return eventService.getAll();
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long add(@RequestBody Event event) {
        return eventService.save(event);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestParam("id") Long id) {
        eventService.remove(id);
    }
}
