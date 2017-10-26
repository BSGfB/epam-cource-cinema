package com.epam.cinema.web.html;

import com.epam.cinema.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getUser() {
        ModelAndView mav = new ModelAndView("index");

        User user = new User();
        user.setFirstName("Bob");
        user.setLastName("Kek");

        mav.addObject("user", user);

        return mav;
    }
}