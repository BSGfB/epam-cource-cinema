package com.epam.cinema.service;

import com.epam.cinema.model.User;

public interface UserService extends CrudService<User> {
    User getByEmail(String email);
}
