package com.epam.cinema.aop;

import com.epam.cinema.model.Event;
import com.epam.cinema.service.EventService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
@Profile("static-data")
public class StaticDataCounterAspect implements CounterAspect {

    private final Map<String, Integer> countMapEventByName = new HashMap<>();
    private final Map<String, Integer> countMapEventPriceByName = new HashMap<>();
    private final Map<String, Integer> countMapBookingTicket = new HashMap<>();

    private final EventService eventService;

    @Autowired
    public StaticDataCounterAspect(EventService eventService) {
        this.eventService = eventService;
    }

    @Before("execution(* com.epam.cinema.service.EventService.getByName(..))")
    public void countEventByName (final JoinPoint joinPoint) {
        increaseCounter(countMapEventByName, (String) joinPoint.getArgs()[0]);
    }

    @Before("execution(* com.epam.cinema.service.EventService.getEventPriceByName(..))")
    public void countEventPriceByName (final JoinPoint joinPoint) {
        increaseCounter(countMapEventPriceByName, (String) joinPoint.getArgs()[0]);
    }

    @Before("execution(* com.epam.cinema.service.BookingService.bookTickets(..))")
    public void countBookingTicket (final JoinPoint joinPoint) {
        Event event = eventService.getById((Long) joinPoint.getArgs()[2]);
        increaseCounter(countMapBookingTicket, event.getName());
    }

    @Override
    public void printCountStatistics() {
        printCountMap(countMapEventByName, "Count called events by name:");
        printCountMap(countMapEventPriceByName, "Count called event's price:");
        printCountMap(countMapBookingTicket, "Count booked tickets for event:");
    }

    private static void increaseCounter(final Map<String, Integer> map, String key) {
        if (!map.containsKey(key))
            map.put(key, 0);

        map.put(key, map.get(key) + 1);
    }

    private static void printCountMap(final Map<String, Integer> map, String text) {
        System.out.println(text);
        map.forEach( (s, integer) -> System.out.println(String.format("%-40s%d", s, integer)));
        System.out.println();
    }
}
