package com.epam.cinema.dao;

import com.epam.cinema.configuration.annotations.Auditoriums;
import com.epam.cinema.model.Auditorium;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Profile("static-data")
public class StaticDataAuditoriumDao implements AuditoriumDao {

    private static Log logger = LogFactory.getLog(StaticDataAuditoriumDao.class);


    private Map<Long, Auditorium> auditoriums = new HashMap<>();
    private static long ID_COUNTER = 0;

    @Autowired
    @Auditoriums
    public void setStartValues(List<Auditorium> auditoriums) {
        auditoriums.forEach(this::save);
    }

    @Override
    public Long save(final Auditorium object) {
        logger.debug("Save: " + object);

        object.setId(ID_COUNTER++);
        this.auditoriums.put(object.getId(), object);
        return object.getId();
    }

    @Override
    public void remove(final Long id) {
        logger.debug("remove: " + id);
        this.auditoriums.remove(id);
    }

    @Override
    public Auditorium getById(final Long id) {
        logger.debug("getById: " + id);

        return this.auditoriums.get(id);
    }

    @Override
    public List<Auditorium> getAll() {
        logger.debug("getAll");

        return new ArrayList<>(this.auditoriums.values());
    }

    @Override
    public Auditorium getByName(final String name) {
        logger.debug("getByName: " + name);

        return this.auditoriums.values()
        .stream()
        .filter(auditorium -> auditorium.getName().equals(name))
        .findFirst()
        .orElse(null);
    }
}
