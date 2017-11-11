package com.epam.cinema.web.html;

import com.epam.cinema.service.BookingService;
import com.epam.cinema.service.EventService;
import com.epam.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Controller
@RequestMapping(value = "/booking")
public class BookingController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @GetMapping({""})
    public ModelAndView booking(@RequestParam(value = "eventId") Long id) {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("bookingFlag", "bookingFlag");
        mav.addObject("event", eventService.getById(id));

        return mav;
    }

    @PostMapping("/add")
    public String getById(@RequestParam(value = "eventId")    Long eventId,
                                @RequestParam(value = "email")      String email,
                                @RequestParam(value = "dataTime") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dataTime,
                                @RequestParam(value = "seat")       Long seat) throws Exception {
        bookingService.bookTickets(userService.getByEmail(email), LocalDateTime.now(), eventId, seat);
        return "redirect:/events";
    }
}
