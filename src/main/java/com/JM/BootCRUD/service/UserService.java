package com.JM.BootCRUD.service;

import com.JM.BootCRUD.model.User;

import java.util.List;

public interface UserService {

    void add(User user, Long[] roleIds);

    void edit(User user, Long[] roleIds);

    void delete(Long id);

    List<User> getAll();

    User getById(Long id);

    User getByUsername(String name);
}
