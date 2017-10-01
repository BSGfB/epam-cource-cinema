package com.epam.cinema.shell.controller;

import com.epam.cinema.model.User;
import com.epam.cinema.service.AuthorizationService;
import com.epam.cinema.service.UserService;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationController implements CommandMarker {

    private AuthorizationService authorizationService;
    private UserService userService;

    public AuthorizationController(AuthorizationService authorizationService, UserService userService) {
        this.authorizationService = authorizationService;
        this.userService = userService;
    }

    @CliCommand(value = {"sign-in"})
    public String signIn(@CliOption(key = "email", mandatory = true) String email) {
        User user = userService.getByEmail(email);
        authorizationService.setUser(user);
        return "Log in as " + user.toString();
    }

    @CliCommand(value = {"log-out"})
    public String signIn() {
        String message =  "Log out from " + authorizationService.getUser();
        authorizationService.setUser(null);
        return message;
    }

    @CliCommand(value = {"who-am-i"})
    public String currentUser() {
        return "You are sign in as " + authorizationService.getUser();
    }
}
