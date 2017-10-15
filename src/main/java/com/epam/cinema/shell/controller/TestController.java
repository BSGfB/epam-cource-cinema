package com.epam.cinema.shell.controller;

import com.epam.cinema.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;

@Component
public class TestController implements CommandMarker {

    @Autowired
    @Qualifier("JdbcBookingTicketCounterService")
    CounterService counterService1;

    @Autowired
    @Qualifier("JdbcEventByNameCounterService")
    CounterService counterService2;

    @Autowired
    @Qualifier("JdbcEventPriceByNameCounterService")
    CounterService counterService3;

    @CliCommand(value = {"test"})
    public void test() {
        System.out.println();
    }
}
