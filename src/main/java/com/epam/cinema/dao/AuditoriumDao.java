package com.epam.cinema.dao;

import com.epam.cinema.model.Auditorium;

public interface AuditoriumDao extends CrudDao<Auditorium> {
    Auditorium getByName(String name);
}
