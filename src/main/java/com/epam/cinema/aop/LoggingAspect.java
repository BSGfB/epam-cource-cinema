package com.epam.cinema.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Around(value = "@annotation(com.epam.cinema.configuration.annotations.Loggable) || execution(* *(..)) &&  within(@com.epam.cinema.configuration.annotations.Loggable *)")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        StringBuffer log = new StringBuffer(joinPoint.getSignature().toShortString());
        if (log.length() > 0)
            log.setLength(log.length() - 3);

        Object[] args = joinPoint.getArgs();
        Arrays.stream(args).forEach(obj -> log.append(obj).append(", "));

        if (log.length() > 0)
            log.setLength(log.length() - 2);

        log.append(")");
        LOGGER.info(log.toString());

        Object joinObject = joinPoint.proceed();

        log.setLength(0);
        log.append(joinPoint.getSignature().toShortString());
        log.append(" return: ").append(joinObject);
        LOGGER.info(log.toString());

        return joinObject;
    }

}
