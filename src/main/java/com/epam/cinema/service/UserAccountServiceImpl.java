package com.epam.cinema.service;

import com.epam.cinema.configuration.annotations.Loggable;
import com.epam.cinema.dao.UserAccountDao;
import com.epam.cinema.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Loggable
@Component
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    UserAccountDao userAccountDao;

    @Override
    public void addMoney(Long userId, BigDecimal bigDecimal) {
        userAccountDao.addMoney(userId, bigDecimal);
    }

    @Override
    public int removeMoney(Long userId, BigDecimal bigDecimal) {
        return userAccountDao.removeMoney(userId, bigDecimal);
    }

    @Override
    public UserAccount getByUserId(Long userId) {
        return userAccountDao.getByUserId(userId);
    }

    @Override
    public Long save(UserAccount object) {
        return userAccountDao.save(object);
    }

    @Override
    public void remove(Long id) {
        userAccountDao.remove(id);
    }

    @Override
    public UserAccount getById(Long id) {
        return userAccountDao.getById(id);
    }

    @Override
    public List<UserAccount> getAll() {
        return userAccountDao.getAll();
    }
}
