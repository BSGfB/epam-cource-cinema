package com.epam.cinema.aop;

import com.epam.cinema.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.*;

@Aspect
@Component
@Profile("static-data")
public class StaticDataDiscountAspect implements DiscountAspect {

    private Map<String, List<UserDiscountInformation>> discounts = new HashMap<>();

    @AfterReturning(pointcut = "@annotation(com.epam.cinema.configuration.annotations.CheckDiscount)", returning = "discount")
    public void checkDiscount(final JoinPoint joinPoint, final Double discount) {
        User user = Arrays.stream(joinPoint.getArgs()).filter(User.class::isInstance).map(User.class::cast).findFirst().get();

        if (!discounts.containsKey(user.getEmail())) {
            discounts.put(user.getEmail(), new ArrayList<UserDiscountInformation>());
        }

        discounts.get(user.getEmail()).add(new UserDiscountInformation(joinPoint.getTarget().getClass().getName().replaceAll(".+\\.", ""), discount));
    }

    @Override
    public void printCheckDiscount() {
        discounts.forEach((email, userDiscountInformation) -> {
            System.out.println(email + ": ");
            System.out.println(String.format("%-40s%-40s", "Name", "Discount"));

            userDiscountInformation.forEach(System.out::println);

            System.out.println();
        });
    }


    class UserDiscountInformation {
        private String discountName;
        private Double discountPrice;

        public UserDiscountInformation(String discountName, Double discountPrice) {
            this.discountName = discountName;
            this.discountPrice = discountPrice;
        }

        public String getDiscountName() {
            return discountName;
        }

        public void setDiscountName(String discountName) {
            this.discountName = discountName;
        }

        public Double getDiscountPrice() {
            return discountPrice;
        }

        public void setDiscountPrice(Double discountPrice) {
            this.discountPrice = discountPrice;
        }

        @Override
        public String toString() {
            return String.format("%-40s%.2f", discountName, discountPrice);
        }
    }
}
