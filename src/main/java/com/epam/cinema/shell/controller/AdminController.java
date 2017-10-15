package com.epam.cinema.shell.controller;

import com.epam.cinema.model.Role;
import com.epam.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

@Component
public class AdminController implements CommandMarker {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @CliCommand(value = {"make-admin"}, help = "Add user admin privileges")
    public void makeAdmin(@CliOption(key = "id", mandatory = false, help = "user id") Long id) {
        try {
            userService.setRole(id, Role.ADMIN);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Access denied! You must be admin.");
        }

    }

}
