package com.JM.BootCRUD.service;

import com.JM.BootCRUD.model.User;

import java.util.List;

public interface UserService {

    void add(User user);

    void edit(User user);

    void delete(Long id);

    List<User> getAll();

    User getById(Long id);

    User getByUsername(String name);
}
