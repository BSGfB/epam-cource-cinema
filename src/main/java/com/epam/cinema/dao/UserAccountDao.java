package com.epam.cinema.dao;

import com.epam.cinema.model.UserAccount;

import java.math.BigDecimal;

public interface UserAccountDao extends CrudDao<UserAccount> {
    void addMoney(Long userId, BigDecimal bigDecimal);
}
