package com.epam.cinema.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "newUserId"
}, namespace = "http://com/epam/cinema/model")
@XmlRootElement(name = "addedUserResponse")
public class AddedUserResponse {
    private Long newUserId;

    public AddedUserResponse() {
    }

    public AddedUserResponse(Long newUserId) {
        this.newUserId = newUserId;
    }

    public Long getNewUserId() {
        return newUserId;
    }

    public void setNewUserId(Long newUserId) {
        this.newUserId = newUserId;
    }
}
