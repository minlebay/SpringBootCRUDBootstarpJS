package com.JM.BootCRUD.service;



import com.JM.BootCRUD.model.Role;

import java.util.List;


public interface RoleService {
    List<Role> getAll();

    Role getById(Long id);

}
