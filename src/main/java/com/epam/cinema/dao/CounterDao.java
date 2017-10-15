package com.epam.cinema.dao;

import com.epam.cinema.model.CounterPair;

import java.util.List;

public interface CounterDao {
    Long save(String name);

    void increaseCounter(String name);

    CounterPair getByName(String name);

    List<CounterPair> getAll();
}
