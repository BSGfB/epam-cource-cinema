package com.epam.cinema.web.html;

import com.epam.cinema.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

@Controller
@RequestMapping("/money")
public class MoneyController {

    @Autowired
    UserAccountService userAccountService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addUser(@RequestParam("money") Double money,
                                @RequestParam("userId") Long userId) {
        userAccountService.addMoney(userId, new BigDecimal(money));
        return new ModelAndView("index");
    }
}
