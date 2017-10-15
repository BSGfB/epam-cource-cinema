package com.epam.cinema.service;

import com.epam.cinema.configuration.annotations.Protected;
import com.epam.cinema.dao.UserDao;
import com.epam.cinema.model.Role;
import com.epam.cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getByEmail(String email) {
        notNull(email, "email must not be null");
        return userDao.getByEmail(email);
    }

    @Override
    @Protected(roles = Role.ADMIN)
    public void setRole(Long userId, Role role) {
        Long roleId = userDao.getRoleId(role);
        userDao.setRole(userId, roleId);
    }

    @Override
    public Long save(User object) {
        notNull(object, "user must not be null");

        return userDao.save(object);
    }

    @Override
    @Protected(roles = Role.ADMIN)
    public void remove(Long id) {
        notNull(id, "id must not be null");
        isTrue(id >= 0, "id must be greater than 0");

        userDao.remove(id);
    }

    @Override
    public User getById(Long id) {
        notNull(id, "id must not be null");
        isTrue(id >= 0, "id must be greater than 0");

        return userDao.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
