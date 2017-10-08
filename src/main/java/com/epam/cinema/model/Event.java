package com.epam.cinema.model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author Yuriy_Tkach
 */
public class Event extends DomainObject {

    private String name;

    private double basePrice;

    private EventRating rating;

    private Map<LocalDateTime, Auditorium> auditoriums = new TreeMap<>();

    public Event() {
    }

    public Event(String name, double basePrice, EventRating rating) {
        this.name = name;
        this.basePrice = basePrice;
        this.rating = rating;
    }

    public Event(String name, double basePrice, EventRating rating, Map<LocalDateTime, Auditorium> auditoriums) {
        this.name = name;
        this.basePrice = basePrice;
        this.rating = rating;
        this.auditoriums = auditoriums;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public EventRating getRating() {
        return rating;
    }

    public void setRating(EventRating rating) {
        this.rating = rating;
    }

    public Map<LocalDateTime, Auditorium> getAuditoriums() {
        return auditoriums;
    }

    public void setAuditoriums(Map<LocalDateTime, Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (Double.compare(event.basePrice, basePrice) != 0) return false;
        if (name != null ? !name.equals(event.name) : event.name != null) return false;
        if (rating != event.rating) return false;
        return auditoriums != null ? auditoriums.equals(event.auditoriums) : event.auditoriums == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(basePrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (auditoriums != null ? auditoriums.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + getId() + '\'' +
                "name='" + name + '\'' +
                ", basePrice=" + basePrice +
                ", rating=" + rating +
                ", \nauditoriums (time|auditorium)\n" + auditoriums.entrySet()
                .stream()
                .map(entry -> "Time: " + entry.getKey().toString() + "\t|\t" + entry.getValue().toString())
                .collect(Collectors.joining("\n")) +
                '}';
    }
}
