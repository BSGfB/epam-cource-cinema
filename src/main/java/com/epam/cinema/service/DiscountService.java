package com.epam.cinema.service;

import com.epam.cinema.model.Event;
import com.epam.cinema.model.User;

import java.time.LocalDateTime;

public interface DiscountService {
    Double getDiscount(Event event, User user, LocalDateTime airDateTime, Long numberOfTickets);
}
