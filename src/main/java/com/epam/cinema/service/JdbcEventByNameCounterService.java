package com.epam.cinema.service;

import com.epam.cinema.dao.CounterDao;
import com.epam.cinema.model.CounterPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Qualifier("JdbcEventByNameCounterService")
@Profile("database")
public class JdbcEventByNameCounterService implements CounterService {

    final CounterDao counterDao;

    @Autowired
    public JdbcEventByNameCounterService(@Qualifier("JdbcEventByNameCounterDao") CounterDao counterDao) {
        this.counterDao = counterDao;
    }

    @Override
    public void increaseCounter(String name) {
        try {
            counterDao.getByName(name);
        } catch (Exception e) {
            counterDao.save(name);
            return;
        }

        counterDao.increaseCounter(name);
    }

    @Override
    public List<CounterPair> getAll() {
        return counterDao.getAll();
    }
}
