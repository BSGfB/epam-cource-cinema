package com.epam.cinema.service;

import com.epam.cinema.dao.UserDao;
import com.epam.cinema.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public Long save(User object) {
        return userDao.save(object);
    }

    @Override
    public void remove(Long id) {
        userDao.remove(id);
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
