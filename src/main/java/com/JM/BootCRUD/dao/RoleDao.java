package com.JM.BootCRUD.dao;

import com.JM.BootCRUD.model.Role;
import java.util.List;

public interface RoleDao {
    List<Role> getAll();

    Role getById(Long id);
}
