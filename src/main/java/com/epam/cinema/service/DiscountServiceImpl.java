package com.epam.cinema.service;

import com.epam.cinema.discount.DiscountStrategy;
import com.epam.cinema.model.Event;
import com.epam.cinema.model.User;

import java.time.LocalDateTime;
import java.util.List;

public class DiscountServiceImpl implements DiscountService {

    private List<DiscountStrategy> discountStrategyList;

    public DiscountServiceImpl(List<DiscountStrategy> discountStrategyList) {
        this.discountStrategyList = discountStrategyList;
    }

    @Override
    public Double calculateDiscount(Event event, User user, LocalDateTime airDateTime, Long numberOfTickets) {
        return discountStrategyList
                .stream()
                .map(discountStrategy -> discountStrategy.calculate(event, user, airDateTime, numberOfTickets))
                .max(Double::compare)
                .orElse(0.0);
    }
}
