package com.epam.cinema.service;

import com.epam.cinema.configuration.annotations.Protected;
import com.epam.cinema.dao.AuditoriumDao;
import com.epam.cinema.model.Auditorium;
import com.epam.cinema.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;
import static org.springframework.util.Assert.notNull;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {


    AuditoriumDao auditoriumDao;

    @Autowired
    public AuditoriumServiceImpl(AuditoriumDao auditoriumDao) {
        this.auditoriumDao = auditoriumDao;
    }

    @Protected(roles = Role.ADMIN)
    @Override
    public Long save(Auditorium object) {
        notNull(object, "Value must not be null");

        return auditoriumDao.save(object);
    }

    @Protected(roles = Role.ADMIN)
    @Override
    public void remove(Long id) {
        notNull(id, "Value must not be null");

        Auditorium auditorium = auditoriumDao.getById(id);
        if (!isNull(auditorium)) {
            auditoriumDao.remove(id);
        }
    }

    @Override
    public Auditorium getById(Long id) {
        notNull(id, "Value must not be null");

        return auditoriumDao.getById(id);
    }

    @Override
    public List<Auditorium> getAll() {
        return auditoriumDao.getAll();
    }

    @Override
    public Auditorium getByName(String name) {
        notNull(name, "Value must not be null");
        return auditoriumDao.getByName(name);
    }
}
