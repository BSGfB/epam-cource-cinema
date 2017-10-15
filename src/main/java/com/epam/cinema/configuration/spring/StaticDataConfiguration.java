package com.epam.cinema.configuration.spring;

import com.epam.cinema.configuration.annotations.Auditoriums;
import com.epam.cinema.configuration.annotations.Events;
import com.epam.cinema.configuration.annotations.Users;
import com.epam.cinema.model.Auditorium;
import com.epam.cinema.model.Event;
import com.epam.cinema.model.EventRating;
import com.epam.cinema.model.User;
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

    private Auditorium auditorium_0 = new Auditorium("Big", 100);

    @Bean
    @Auditoriums
    public List<Auditorium> auditoriums() {
        List<Auditorium> auditoriums = new ArrayList<>();
        auditoriums.add(new Auditorium("Small", 50));
        auditoriums.add(auditorium_0);

        return auditoriums;
    }

    @Bean
    @Events
    public List<Event> events() {
        List<Event> events = new ArrayList<>();

        Map<LocalDateTime, Auditorium> map = new HashMap<>();
        map.put(LocalDateTime.of(2017, 10, 1, 18, 30), auditorium_0);

        events.add(new Event("Kingsman 2", 5, EventRating.HIGH, map));

        return events;
    }

    @Bean
    @Users
    public List<User> users() {
        List<User> users = new ArrayList<>();
        users.add(new User("Siarhei", "Blashuk", "Siarhei_Blashuk@epam.com", LocalDate.of(1996, 7, 18)));
        users.add(new User("Bob", "Bob", "Bob_Bob@epam.com", LocalDate.of(1997, 2, 21)));

        return users;
    }

}
