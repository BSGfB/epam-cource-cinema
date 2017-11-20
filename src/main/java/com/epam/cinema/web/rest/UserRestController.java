package com.epam.cinema.web.rest;

import com.epam.cinema.configuration.annotations.Loggable;
import com.epam.cinema.model.User;
import com.epam.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Loggable
@RestController
@RequestMapping(value = "/rest/users")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    public User getUser(@RequestParam("id") Long id){
        return userService.getById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public List<User> getUsers(){
        return userService.getAll();
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long add(@RequestBody User user) {
        return userService.save(user);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestParam("id") Long id) {
        userService.remove(id);
    }
}
