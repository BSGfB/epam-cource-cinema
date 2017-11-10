package com.epam.cinema.model;

import java.math.BigDecimal;

public class UserAccount extends DomainObject {
    private Long userAccountId;
    private Long userId;
    private BigDecimal money;

    public UserAccount() {
    }

    public UserAccount(Long userAccountId, Long userId, BigDecimal money) {
        this.userAccountId = userAccountId;
        this.userId = userId;
        this.money = money;
    }

    public Long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "userAccountId=" + userAccountId +
                ", userId=" + userId +
                ", money=" + money +
                '}';
    }
}
