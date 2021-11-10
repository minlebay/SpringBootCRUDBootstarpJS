package com.JM.BootCRUD.dao;

import com.JM.BootCRUD.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();

    User getById(Long id);

    void delete(Long id);

    void edit(User user);

    void add(User user);

    User getByUsername(String username);
}
