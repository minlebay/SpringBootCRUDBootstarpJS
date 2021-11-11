package com.JM.BootCRUD.dao;

import com.JM.BootCRUD.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public User getById(Long id){
        return entityManager.createQuery("SELECT user from User user " +
                        "JOIN FETCH user.roles" +
                        " WHERE user.id=:id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<User> getAll(){
        return entityManager.createQuery("SELECT DISTINCT user from User user " +
                        "JOIN FETCH user.roles ", User.class)
                .getResultList();
    }

    @Override
    public void add(User user){
        entityManager.persist(user);
    }

    @Override
    public User getByUsername(String username) {
        return entityManager.createQuery("SELECT user from User user " +
                        "JOIN FETCH user.roles " +
                        "WHERE user.username=:username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public void edit(User user){
        entityManager.merge(user);
    }

    @Override
    public void delete(Long id){
        entityManager.remove(entityManager.find(User.class, id));
    }
}
