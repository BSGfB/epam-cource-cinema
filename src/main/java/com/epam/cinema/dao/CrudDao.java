package com.epam.cinema.dao;

import com.epam.cinema.model.DomainObject;

import java.util.List;

public interface CrudDao <T extends DomainObject> {
    Long save(T object);

    void remove(Long id);

    T getById(Long id);

    List<T> getAll();
}
