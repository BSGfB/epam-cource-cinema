package com.epam.cinema.discount;

import com.epam.cinema.model.Event;
import com.epam.cinema.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Every10thTicketDiscountStrategyTest {
    public static final long AMOUNT_BOUGHT_SEATS = 22l;
    public static final int YEAR = 2017;
    public static final int MONTH = 1;
    public static final int HOUR = 0;
    public static final int MINUTE = 0;
    private Event event;
    private User user;

    @Before
    public void init() {
        event = new Event();
        event.setBasePrice(10);

        user = new User();
        user.setBirthday(LocalDate.of(1997, 1, 10));


    }

    @Test
    public void calculate() throws Exception {
        DiscountStrategy discountStrategy = new Every10thTicketDiscountStrategy();

        Double discount_1 = discountStrategy.calculate(event, user, LocalDateTime.of(YEAR, MONTH, 10, HOUR, MINUTE), AMOUNT_BOUGHT_SEATS);
        Assert.assertEquals(new Double(10), discount_1);
    }

}