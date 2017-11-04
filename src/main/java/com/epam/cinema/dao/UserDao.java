package com.epam.cinema.dao;

import com.epam.cinema.model.Role;
import com.epam.cinema.model.User;

import java.util.List;

public interface UserDao extends CrudDao<User> {
    User getByEmail(String email);
    void addMessage(Long id, String messageText);
    void setRole(Long user_id, Long role_id);
    Long getRoleId(Role role);
    List<Role> getRoles(Long userId);
}
