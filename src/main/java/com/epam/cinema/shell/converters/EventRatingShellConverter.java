package com.epam.cinema.shell.converters;

import com.epam.cinema.model.EventRating;
import org.springframework.shell.core.Completion;
import org.springframework.shell.core.Converter;
import org.springframework.shell.core.MethodTarget;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventRatingShellConverter implements Converter<EventRating> {
    @Override
    public boolean supports(Class<?> type, String optionContext) {
        return EventRating.class.isAssignableFrom(type);
    }

    @Override
    public EventRating convertFromText(String value, Class<?> targetType, String optionContext) {
        return EventRating.valueOf(value.trim().toUpperCase());
    }

    @Override
    public boolean getAllPossibleValues(List<Completion> completions, Class<?> targetType, String existingData, String optionContext, MethodTarget target) {
        return false;
    }
}
