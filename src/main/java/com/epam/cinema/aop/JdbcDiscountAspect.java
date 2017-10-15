package com.epam.cinema.aop;

import com.epam.cinema.dto.UserDiscount;
import com.epam.cinema.model.User;
import com.epam.cinema.service.DiscountService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Profile("database")
public class JdbcDiscountAspect implements DiscountAspect {

    private final DiscountService discountService;

    @Autowired
    public JdbcDiscountAspect(final DiscountService discountService) {
        this.discountService = discountService;
    }

    @AfterReturning(pointcut = "@annotation(com.epam.cinema.configuration.annotations.CheckDiscount)", returning = "discount")
    public void checkDiscount(final JoinPoint joinPoint, final Double discount) {
        User user = Arrays.stream(joinPoint.getArgs()).filter(User.class::isInstance).map(User.class::cast).findFirst().get();
        UserDiscount userDiscount = new UserDiscount();
        userDiscount.setUserId(user.getId());
        userDiscount.setDiscountName(joinPoint.getTarget().getClass().getName().replaceAll(".+\\.", ""));
        userDiscount.setPrice(discount);

        discountService.save(userDiscount);
    }

    @Override
    public void printCheckDiscount() {

        System.out.println(String.format("%-40s%-40s\t Email", "Name", "Discount"));
        discountService.getAll().forEach(userDiscount -> {
            System.out.println(String.format("%-40s%.2f\t [%s]", userDiscount.getDiscountName(), userDiscount.getPrice(), userDiscount.getEmail()));
        });
    }
}
