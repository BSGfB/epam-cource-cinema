package com.epam.cinema.shell.converters;

import org.springframework.context.annotation.Profile;
import org.springframework.shell.core.Completion;
import org.springframework.shell.core.Converter;
import org.springframework.shell.core.MethodTarget;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Profile("spring-shell")
@Component
public class LocalDateTimeShellConverter implements Converter<LocalDateTime> {
    @Override
    public boolean supports(Class<?> type, String optionContext) {
        return LocalDateTime.class.isAssignableFrom(type);
    }

    @Override
    public LocalDateTime convertFromText(String value, Class<?> targetType, String optionContext) {
        return LocalDateTime.parse(value);
    }

    @Override
    public boolean getAllPossibleValues(List<Completion> completions, Class<?> targetType, String existingData, String optionContext, MethodTarget target) {
        return false;
    }
}
