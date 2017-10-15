package com.epam.cinema.model;

import java.util.Objects;

/**
 * @author Yuriy_Tkach
 */
public enum EventRating {

    LOW("low"),
    MID("mid"),
    HIGH("high"),
    UNDEFINED("undefined");

    String eventRating;

    EventRating(final String eventRating) {
        this.eventRating = eventRating;
    }

    public static EventRating get(final String eventRatingName) {
        if (Objects.isNull(eventRatingName)) {
            return UNDEFINED;
        }

        for (EventRating rating: values()) {
            if (rating.eventRating.equalsIgnoreCase(eventRatingName))
                return rating;
        }

        return UNDEFINED;
    }
}
