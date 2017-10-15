package com.epam.cinema.aop;

import com.epam.cinema.dao.UserDao;
import com.epam.cinema.model.Event;
import com.epam.cinema.model.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Aspect
@Component
public class LuckyWinnerAspect {

    private static final Random rnd = new Random();
    private static final Integer LUCKY_CHANCE = 50;

    private UserDao userDao;

    @Autowired
    public LuckyWinnerAspect(UserDao userDao) {
        this.userDao = userDao;
    }

    @Around("execution(* com.epam.cinema.service.TicketService.getTicketPrice(..))")
    public Double luckyWinner(ProceedingJoinPoint joinPoint) throws Throwable {
        Double price =  (Double) joinPoint.proceed();

        if (isLucky()) {
            Event event = (Event) joinPoint.getArgs()[0];
            User user = (User) joinPoint.getArgs()[1];
            userDao.addMessage(user.getId(), "You win free tickets for " + event.getName());

            return 0.0d;
        }

        return price;
    }

    private static Boolean isLucky() {
        return rnd.nextInt(100) <= LUCKY_CHANCE;
    }
}
