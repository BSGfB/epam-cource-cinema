package com.epam.cinema.web.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StartController {
    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public ModelAndView startPage() {
        return new ModelAndView("index");
    }
}
