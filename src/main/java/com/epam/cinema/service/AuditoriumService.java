package com.epam.cinema.service;

import com.epam.cinema.model.Auditorium;

public interface AuditoriumService extends CrudService<Auditorium> {
    Auditorium getByName(String name);
}
