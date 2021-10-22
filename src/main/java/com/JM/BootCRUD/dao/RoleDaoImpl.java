package com.JM.BootCRUD.dao;

import com.JM.BootCRUD.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAll() {
        return entityManager.createQuery("SELECT role from Role role", Role.class)
                .getResultList();
    }

    @Override
    public Role getById(Long id) {
        return entityManager.find(Role.class, id);
    }

}
