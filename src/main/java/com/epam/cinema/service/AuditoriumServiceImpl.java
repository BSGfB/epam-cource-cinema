package com.epam.cinema.service;

import com.epam.cinema.dao.AuditoriumDao;
import com.epam.cinema.model.Auditorium;

import java.util.List;

public class AuditoriumServiceImpl implements AuditoriumService {

    AuditoriumDao auditoriumDao;

    public AuditoriumServiceImpl(AuditoriumDao auditoriumDao) {
        this.auditoriumDao = auditoriumDao;
    }

    @Override
    public Long save(Auditorium object) {
        return auditoriumDao.save(object);
    }

    @Override
    public void remove(Long id) {
        auditoriumDao.remove(id);
    }

    @Override
    public Auditorium getById(Long id) {
        return auditoriumDao.getById(id);
    }

    @Override
    public List<Auditorium> getAll() {
        return auditoriumDao.getAll();
    }

    @Override
    public Auditorium getByName(String name) {
        return auditoriumDao.getByName(name);
    }
}
