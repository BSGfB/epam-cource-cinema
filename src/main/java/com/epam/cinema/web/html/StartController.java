package com.epam.cinema.web.html;

import com.epam.cinema.configuration.annotations.Loggable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Loggable
public class StartController {
    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public ModelAndView startPage() {
        return new ModelAndView("index");
    }

    @RequestMapping(value="/login")
    public String homePage() {
        return "login";
    }
}
