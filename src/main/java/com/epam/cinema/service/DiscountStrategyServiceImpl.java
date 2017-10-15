package com.epam.cinema.service;

import com.epam.cinema.configuration.annotations.DiscountStrategies;
import com.epam.cinema.discount.DiscountStrategy;
import com.epam.cinema.model.Event;
import com.epam.cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DiscountStrategyServiceImpl implements DiscountStrategyService {

    private List<DiscountStrategy> discountStrategyList;

    @Autowired
    @DiscountStrategies
    public DiscountStrategyServiceImpl(List<DiscountStrategy> discountStrategyList) {
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
