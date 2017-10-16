package com.epam.cinema.aop;

import com.epam.cinema.configuration.annotations.Protected;
import com.epam.cinema.model.Role;
import com.epam.cinema.service.AuthorizationService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Profile("spring-shell")
public class SecurityAspect {
    private AuthorizationService authorizationService;

    public SecurityAspect(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Around(value = "@annotation(com.epam.cinema.configuration.annotations.Protected) && @annotation(protectedAnnotation)")
    public Object protectedMethod(final ProceedingJoinPoint joinPoint, final Protected protectedAnnotation) throws Throwable {
        final Role[] roles = protectedAnnotation.roles();
        final Role currentRole = authorizationService.getUser().getRole();

        final boolean isCorrectRole = Arrays.stream(roles).anyMatch(role -> role.equals(Role.ALL) || role.equals(currentRole));

        if (authorizationService.isAuthorized() && isCorrectRole)
            return joinPoint.proceed();

        throw new Exception("You cannot access current field");
    }
}
