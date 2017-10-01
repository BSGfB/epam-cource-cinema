package com.epam.cinema.shell.controller;

import com.epam.cinema.model.User;
import com.epam.cinema.service.UserService;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class UserController implements CommandMarker {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @CliCommand(value = {"users"}, help = "Return all users")
    public String getUsers(@CliOption(key = "all", mandatory = false, help = "Get all") String all,
                           @CliOption(key = "id", mandatory = false, help = "Get user by id") Long id,
                           @CliOption(key = "email", mandatory = false, help = "Get by email") String email) {
        if (!isNull(id))
            return userService.getById(id).toString();
        if (!isNull(email))
            return userService.getByEmail(email).toString();

        return userService.getAll().stream().map(User::toString).collect(Collectors.joining("\n"));
    }

    @CliCommand(value = {"rm-user"}, help = "Delete user")
    public void removeUser(@CliOption(key = "id", mandatory = false) Long id) {
        userService.remove(id);
    }

    @CliCommand(value = {"create-user"}, help = "Create new user")
    public String createUser(@CliOption(key = "firstName", mandatory = true) String firstName,
                             @CliOption(key = "lastName", mandatory = true) String lastName,
                             @CliOption(key = "email", mandatory = true) String email,
                             @CliOption(key = "birthday", mandatory = true) LocalDate birthday) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setBirthday(birthday);

        return "Added new user with id: " + userService.save(user);
    }
}
