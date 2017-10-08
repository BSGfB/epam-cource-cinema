package com.epam.cinema.discount;

import com.epam.cinema.configuration.annotations.CheckDiscount;
import com.epam.cinema.model.Event;
import com.epam.cinema.model.User;

import java.time.LocalDateTime;

public class BirthdayDiscountStrategy implements DiscountStrategy {

    @CheckDiscount
    @Override
    public Double calculate(Event event, User user, LocalDateTime time, Long amountBoughtSeats) {
        int dFrom = time.toLocalDate().minusDays(2).getDayOfYear();
        int dTo = time.toLocalDate().plusDays(2).getDayOfYear();

        if (dFrom <= user.getBirthday().getDayOfYear() && dTo >= user.getBirthday().getDayOfYear()) {
            return event.getBasePrice() * amountBoughtSeats * 0.05d;
        }

        return 0d;
    }
}
