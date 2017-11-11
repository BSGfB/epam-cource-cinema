package com.epam.cinema.service;

import com.epam.cinema.model.User;

import java.time.LocalDateTime;

public interface BookingService {
    Double bookTickets(User user, LocalDateTime time, Long eventId, Long seats) throws Exception;
}
