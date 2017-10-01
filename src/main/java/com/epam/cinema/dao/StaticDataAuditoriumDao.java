package com.epam.cinema.dao;

import com.epam.cinema.model.Auditorium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaticDataAuditoriumDao implements AuditoriumDao {

    private Map<Long, Auditorium> auditoriums = new HashMap<>();
    private static long ID_COUNTER = 0;

    public void setStartValues(List<Auditorium> auditoriums) {
        auditoriums.forEach(this::save);
    }

    @Override
    public Long save(Auditorium object) {
        object.setId(ID_COUNTER++);
        this.auditoriums.put(object.getId(), object);
        return object.getId();
    }

    @Override
    public void remove(Long id) {
        this.auditoriums.remove(id);
    }

    @Override
    public Auditorium getById(Long id) {
        return this.auditoriums.get(id);
    }

    @Override
    public List<Auditorium> getAll() {
        return new ArrayList<>(this.auditoriums.values());
    }

    @Override
    public Auditorium getByName(String name) {
        return this.auditoriums.values()
        .stream()
        .filter(auditorium -> auditorium.getName().equals(name))
        .findFirst()
        .orElse(null);
    }
}
