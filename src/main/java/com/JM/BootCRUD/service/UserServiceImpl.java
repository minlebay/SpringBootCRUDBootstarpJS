package com.JM.BootCRUD.service;

import com.JM.BootCRUD.dao.RoleDao;
import com.JM.BootCRUD.dao.UserDao;
import com.JM.BootCRUD.model.Role;
import com.JM.BootCRUD.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, RoleDao roleService, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void add(User user, Long[] roleIds) {
        Set<Role> roles = new HashSet<>();
        Arrays.stream(roleIds).forEach(roleId -> roles.add(roleService.getById(roleId)));
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.add(user);
    }

    @Override
    @Transactional
    public void edit(User user, Long[] roleIds) {
        Set<Role> roles = new HashSet<>();
        Arrays.stream(roleIds).forEach(roleId -> roles.add(roleService.getById(roleId)));
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.edit(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getByUsername(String name) {
        return userDao.getByUsername(name);
    }
}
