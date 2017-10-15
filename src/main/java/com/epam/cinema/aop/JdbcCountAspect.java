package com.epam.cinema.aop;


import com.epam.cinema.model.CounterPair;
import com.epam.cinema.model.Event;
import com.epam.cinema.service.CounterService;
import com.epam.cinema.service.EventService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
@Profile("database")
public class JdbcCountAspect implements CounterAspect {

    private final EventService eventService;

    final CounterService jdbcBookingTicketCounterService;

    final CounterService jdbcEventByNameCounterService;

    final CounterService jdbcEventPriceByNameCounterService;

    @Autowired
    public JdbcCountAspect(EventService eventService,
                           @Qualifier("JdbcEventPriceByNameCounterService") CounterService jdbcEventPriceByNameCounterService,
                           @Qualifier("JdbcEventByNameCounterService") CounterService jdbcEventByNameCounterService,
                           @Qualifier("JdbcBookingTicketCounterService") CounterService jdbcBookingTicketCounterService) {
        this.eventService = eventService;
        this.jdbcEventPriceByNameCounterService = jdbcEventPriceByNameCounterService;
        this.jdbcEventByNameCounterService = jdbcEventByNameCounterService;
        this.jdbcBookingTicketCounterService = jdbcBookingTicketCounterService;
    }

    @Before("execution(* com.epam.cinema.service.EventService.getByName(..))")
    public void countEventByName (final JoinPoint joinPoint) {
        jdbcEventByNameCounterService.increaseCounter((String) joinPoint.getArgs()[0]);
    }

    @Before("execution(* com.epam.cinema.service.EventService.getEventPriceByName(..))")
    public void countEventPriceByName (final JoinPoint joinPoint) {
        jdbcEventPriceByNameCounterService.increaseCounter((String) joinPoint.getArgs()[0]);
    }

    @Before("execution(* com.epam.cinema.service.BookingService.bookTickets(..))")
    public void countBookingTicket (final JoinPoint joinPoint) {
        Event event = eventService.getById((Long) joinPoint.getArgs()[2]);
        jdbcBookingTicketCounterService.increaseCounter(event.getName());
    }

    @Override
    public void printCountStatistics() {
        System.out.println("[With jdbc]");
        printCountList(jdbcEventByNameCounterService.getAll(), "Amount called events by name:");
        printCountList(jdbcEventPriceByNameCounterService.getAll(), "Amount called event's price:");
        printCountList(jdbcBookingTicketCounterService.getAll(), "Amount booked tickets for event:");
    }

    private static void printCountList(final List<CounterPair> list, final String text) {
        list.forEach(counterPair -> System.out.println(String.format("%-40s%d\t\t[%s]", counterPair.getName(), counterPair.getCounter(), text)));
        System.out.println();
    }
}
