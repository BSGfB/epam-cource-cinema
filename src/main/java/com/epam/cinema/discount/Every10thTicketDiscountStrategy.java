package com.epam.cinema.discount;

import com.epam.cinema.model.Event;
import com.epam.cinema.model.User;

import java.time.LocalDateTime;

public class Every10thTicketDiscountStrategy implements DiscountStrategy {
    @Override
    public Double calculate(Event event, User user, LocalDateTime time, Long amountBoughtSeats) {
        return ((int) (amountBoughtSeats / 10)) * event.getBasePrice() * 0.5;
    }
}
