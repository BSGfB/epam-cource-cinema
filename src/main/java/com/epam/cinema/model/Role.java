package com.epam.cinema.model;

import java.util.Objects;

public enum Role {
    ADMIN("admin"),
    USER("user"),
    MANAGER("manager"),
    ALL("all"),
    UNDEFINED("undefined");

    String role;

    Role(final String role) {
        this.role = role;
    }

    public static Role get(final String role) {
        if (Objects.isNull(role)) {
            return UNDEFINED;
        }

        for (Role cRole: values()) {
            if (cRole.role.equalsIgnoreCase(role))
                return cRole;
        }

        return UNDEFINED;
    }
}
