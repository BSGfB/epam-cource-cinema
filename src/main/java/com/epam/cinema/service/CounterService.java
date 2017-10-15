package com.epam.cinema.service;

import com.epam.cinema.model.CounterPair;

import java.util.List;

public interface CounterService {

    void increaseCounter(String name);

    List<CounterPair> getAll();
}
