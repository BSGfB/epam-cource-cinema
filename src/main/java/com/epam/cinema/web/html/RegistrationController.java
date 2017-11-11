package com.epam.cinema.web.html;

import com.epam.cinema.model.Role;
import com.epam.cinema.model.User;
import com.epam.cinema.model.UserAccount;
import com.epam.cinema.service.UserAccountService;
import com.epam.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    UserAccountService userAccountService;


    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ModelAndView getRegistrationForm() {
        ModelAndView registration = new ModelAndView("index");
        registration.addObject("registration", "registration");

        return registration;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addUser(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("email") String email,
                                @RequestParam("password") String password,
                                @RequestParam("birthday") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthday) {
        User user = new User(firstName, lastName, email, birthday);
        user.setPassword(password);

        Long id = userService.save(user);
        userService.setRole(id, Role.USER);

        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(id);
        userAccount.setMoney(new BigDecimal(0.0));

        userAccountService.save(userAccount);

        return new ModelAndView("index");
    }
}
