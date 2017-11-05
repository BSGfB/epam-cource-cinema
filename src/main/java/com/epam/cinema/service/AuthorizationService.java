package com.epam.cinema.service;


import com.epam.cinema.configuration.annotations.Loggable;
import com.epam.cinema.model.User;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Loggable
@Service
public class AuthorizationService {
    private User user = null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean isAuthorized() {
        return !isNull(user);
    }
}
