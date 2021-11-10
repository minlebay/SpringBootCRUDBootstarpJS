package com.JM.BootCRUD.service;

import com.JM.BootCRUD.dao.RoleDao;
import com.JM.BootCRUD.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAll() {
        return roleDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Role getById(Long id) {
        return roleDao.getById(id);
    }
}
