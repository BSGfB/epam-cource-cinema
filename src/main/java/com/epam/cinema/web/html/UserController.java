package com.epam.cinema.web.html;

import com.epam.cinema.model.User;
import com.epam.cinema.service.UserAccountService;
import com.epam.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserAccountService userAccountService;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public ModelAndView getAll() {
        ModelAndView mav = new ModelAndView("index");

        mav.addObject("users", userService.getAll());

        return mav;
    }

    @RequestMapping(value = "/byEmail", method = RequestMethod.GET)
    public ModelAndView getByEmail(@RequestParam(value = "email") String email) {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("user", userService.getByEmail(email));

        return mav;
    }

    @RequestMapping(value = "/byId", method = RequestMethod.GET)
    public ModelAndView getById(@RequestParam(value = "id") Long id) {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("user", userService.getById(id));

        return mav;
    }

    @RequestMapping(value = "/pdf", method = RequestMethod.GET, produces={"application/pdf"})
    public ModelAndView getPdf() {
        return new ModelAndView("index");
    }



    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView userProfile(@RequestParam(value = "email") String email) {
        User byEmail = userService.getByEmail(email);

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("user", byEmail);
        mav.addObject("userAccount", userAccountService.getByUserId(byEmail.getId()));

        return mav;
    }
}
