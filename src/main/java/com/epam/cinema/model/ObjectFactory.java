package com.epam.cinema.model;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    GetUsersRequest createGetUsersRequest() {
        return new GetUsersRequest();
    }

    AddUserRequest createAddUserRequest() {
        return new AddUserRequest();
    }

    AddedUserResponse createAddedUserResponse() {
        return new AddedUserResponse();
    }

    UsersResponse createUsersResponse() {
        return new UsersResponse();
    }
}
