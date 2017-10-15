package com.epam.cinema.dto;

import com.epam.cinema.model.Auditorium;

import java.time.LocalDateTime;

public class AuditoriumWithDateTime {
    private LocalDateTime startTime;
    private Auditorium auditorium;

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }
}
