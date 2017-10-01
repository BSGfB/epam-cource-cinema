package com.epam.cinema.dao;

import com.epam.cinema.model.User;

public interface UserDao extends CrudDao<User> {
    User getByEmail(String email);
}
