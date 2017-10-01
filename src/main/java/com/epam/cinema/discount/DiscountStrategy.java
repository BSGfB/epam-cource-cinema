package com.epam.cinema.discount;

import com.epam.cinema.model.Event;
import com.epam.cinema.model.User;

import java.time.LocalDateTime;

public interface DiscountStrategy {
    Double calculate(Event event, User user, LocalDateTime time, Long amountBoughtSeats);
}
