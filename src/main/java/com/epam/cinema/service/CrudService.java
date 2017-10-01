package com.epam.cinema.service;

import com.epam.cinema.model.DomainObject;

import java.util.List;

public interface CrudService <T extends DomainObject> {
    Long save(T object);

    void remove(Long id);

    T getById(Long id);

    List<T> getAll();
}
