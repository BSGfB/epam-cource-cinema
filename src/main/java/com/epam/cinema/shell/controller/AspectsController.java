package com.epam.cinema.shell.controller;

import com.epam.cinema.aop.CounterAspect;
import com.epam.cinema.aop.DiscountAspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;

@Component
public class AspectsController implements CommandMarker {

    private final DiscountAspect discountAspect;
    private final CounterAspect counterAspect;

    @Autowired
    public AspectsController(DiscountAspect discountAspect, CounterAspect counterAspect) {
        this.discountAspect = discountAspect;
        this.counterAspect = counterAspect;
    }


    @CliCommand(value = {"stat-count"}, help = "Return all counted statistics")
    public void showCount() {
        System.out.println(String.format("%-40s%-40s", "Name:", "Count"));
        counterAspect.printCountStatistics();
    }

    @CliCommand(value = {"stat-disk"}, help = "Return all discount statistics per user")
    public void showCheckDiscount() {
        discountAspect.printCheckDiscount();
    }
}
