package com.epam.cinema.service;

import com.epam.cinema.configuration.annotations.Loggable;
import com.epam.cinema.dao.DiscountDao;
import com.epam.cinema.dto.UserDiscount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Loggable
@Component
@Profile("database")
public class DiscountServiceImpl implements DiscountService {

    private final DiscountDao discountDao;

    @Autowired
    public DiscountServiceImpl(DiscountDao discountDao) {
        this.discountDao = discountDao;
    }

    @Override
    public Long save(UserDiscount userDiscount) {
        return discountDao.save(userDiscount);
    }

    @Override
    public List<UserDiscount> getAll() {
        return discountDao.getAll();
    }
}
