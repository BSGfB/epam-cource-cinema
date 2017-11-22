package com.epam.cinema.web.soap;

import com.epam.cinema.model.*;
import com.epam.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoint {

    final UserService userService;

    @Autowired
    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @PayloadRoot(namespace = "http://com/epam/cinema/model", localPart = "getUsersRequest")
    @ResponsePayload
    public UsersResponse userResponse(@RequestPayload GetUsersRequest request) {
        System.out.println("getUsersRequest: " + request);

        UsersResponse response = new UsersResponse();
        response.setUserList(userService.getAll());
        return response;
    }

    @PayloadRoot(namespace = "http://com/epam/cinema/model", localPart = "addUserRequest")
    @ResponsePayload
    public AddedUserResponse addUserResponse(@RequestPayload AddUserRequest request) {
        System.out.println("AddUserRequest: " + request);

        return new AddedUserResponse(userService.save(request.getUser()));
    }
}
