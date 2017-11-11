package com.epam.cinema.service;

import com.epam.cinema.model.UserAccount;

import java.math.BigDecimal;

public interface UserAccountService extends CrudService<UserAccount> {
    void addMoney(Long userId, BigDecimal bigDecimal);
    int removeMoney(Long userId, BigDecimal bigDecimal);
    UserAccount getByUserId(Long userId);
}
