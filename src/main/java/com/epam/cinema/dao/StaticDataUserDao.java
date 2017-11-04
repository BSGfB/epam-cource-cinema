package com.epam.cinema.dao;

import com.epam.cinema.configuration.annotations.Users;
import com.epam.cinema.model.Role;
import com.epam.cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Profile("static-data")
public class StaticDataUserDao implements UserDao {

    private Map<Long, User> users = new HashMap<>();

    private static long ID_COUNTER = 0;

    @Autowired
    @Users
    public void setStartValues(List<User> users) {
        users.forEach(this::save);
    }

    @Override
    public Long save(User user) {
        user.setId(ID_COUNTER++);
        users.put(user.getId(), user);
        return user.getId();
    }

    @Override
    public void remove(Long id) {
        users.remove(id);
    }

    @Override
    public User getById(Long id) {
        return users.get(id);
    }

    @Override
    public User getByEmail(String email) {
        return users.values().stream()
            .filter(user -> user.getEmail().equals(email))
            .findFirst()
            .orElse(null);
    }

    @Override
    public void addMessage(Long id, String messageText) {
        users.get(id).getMessages().add(messageText);
    }

    @Override
    public void setRole(Long user_id, Long role_id) {
        users.get(user_id).setRole(Role.values()[Math.toIntExact(role_id)]);
    }

    @Override
    public Long getRoleId(Role role) {
        if (role == Role.ADMIN) return 1L;

        return 0L;
    }

    @Override
    public List<Role> getRoles(Long userId) {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(users.get(userId).getRole());

        return roles;
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }
}
