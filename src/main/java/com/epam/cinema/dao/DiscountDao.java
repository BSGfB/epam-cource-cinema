package com.epam.cinema.dao;

import com.epam.cinema.dto.UserDiscount;

import java.util.List;

public interface DiscountDao {
    Long save (UserDiscount userDiscount);

    List<UserDiscount> getAll();
}
