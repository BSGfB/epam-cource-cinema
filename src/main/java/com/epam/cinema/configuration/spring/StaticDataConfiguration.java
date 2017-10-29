package com.epam.cinema.configuration.spring;

import com.epam.cinema.configuration.annotations.Auditoriums;
import com.epam.cinema.configuration.annotations.Events;
import com.epam.cinema.configuration.annotations.Tickets;
import com.epam.cinema.configuration.annotations.Users;
import com.epam.cinema.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Profile("static-data")
public class StaticDataConfiguration {

    private Auditorium auditorium = new Auditorium("Big", 100);
    private User user = new User("Siarhei", "Blashuk", "Siarhei_Blashuk@epam.com", LocalDate.of(1996, 7, 18));
    private LocalDateTime eventDateTime = LocalDateTime.of(2017, 10, 1, 18, 30);

    private Event event;

    {
        Map<LocalDateTime, Auditorium> map = new HashMap<>();
        map.put(eventDateTime, auditorium);

        event = new Event("Kingsman 2", 5, EventRating.HIGH, map);
    }



    @Bean
    @Auditoriums
    public List<Auditorium> auditoriums() {
        List<Auditorium> auditoriums = new ArrayList<>();
        auditoriums.add(new Auditorium("Small", 50));
        auditoriums.add(auditorium);

        return auditoriums;
    }

    @Bean
    @Events
    public List<Event> events() {
        List<Event> events = new ArrayList<>();
        events.add(event);

        return events;
    }

    @Bean
    @Users
    public List<User> users() {
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(new User("Bob", "Bob", "Bob_Bob@epam.com", LocalDate.of(1997, 2, 21)));

        return users;
    }

    @Bean
    @Tickets
    public List<Ticket> tickets() {
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket(user, event, eventDateTime, 10));
        tickets.add(new Ticket(user, event, eventDateTime, 11));
        tickets.add(new Ticket(user, event, eventDateTime, 12));
        tickets.add(new Ticket(user, event, eventDateTime, 13));
        tickets.add(new Ticket(user, event, eventDateTime, 14));
        tickets.add(new Ticket(user, event, eventDateTime, 15));
        tickets.add(new Ticket(user, event, eventDateTime, 16));

        return tickets;
    }
}
