package com.epam.cinema.configuration.spring;

import com.epam.cinema.configuration.annotations.DiscountStrategies;
import com.epam.cinema.discount.BirthdayDiscountStrategy;
import com.epam.cinema.discount.DiscountStrategy;
import com.epam.cinema.discount.Every10thTicketDiscountStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscountStrategyConfiguration {

//    @Bean
//    @DiscountStrategies
//    public List<DiscountStrategy> discountStrategies() {
//        List<DiscountStrategy> discountStrategies = new ArrayList<>();
//        discountStrategies.add(new BirthdayDiscountStrategy());
//        discountStrategies.add(new Every10thTicketDiscountStrategy());
//
//        return discountStrategies;
//    }

    @Bean
    @DiscountStrategies
    public DiscountStrategy BirthdayDiscountStrategy() {
        return new BirthdayDiscountStrategy();
    }

    @Bean
    @DiscountStrategies
    public DiscountStrategy Every10thTicketDiscountStrategy() {
        return new Every10thTicketDiscountStrategy();
    }

}
