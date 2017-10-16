package com.epam.cinema.shell.converters;

import org.springframework.context.annotation.Profile;
import org.springframework.shell.core.Completion;
import org.springframework.shell.core.Converter;
import org.springframework.shell.core.MethodTarget;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Profile("spring-shell")
@Component
public class LocalDataShellConverter implements Converter<LocalDate> {
    @Override
    public boolean supports(Class<?> type, String optionContext) {
        return LocalDate.class.isAssignableFrom(type);
    }

    @Override
    public LocalDate convertFromText(String value, Class<?> targetType, String optionContext) {
        return LocalDate.parse(value);
    }

    @Override
    public boolean getAllPossibleValues(List<Completion> completions, Class<?> targetType, String existingData, String optionContext, MethodTarget target) {
        return false;
    }
}
