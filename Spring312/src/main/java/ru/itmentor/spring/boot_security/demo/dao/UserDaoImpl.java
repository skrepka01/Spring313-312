package ru.itmentor.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User findUserById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User save(User entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public void delete(Long id) {
        em.remove(em.find(User.class,id));
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> typedQuery = em.createQuery("SELECT u FROM User u WHERE u.username = :name", User.class);
        typedQuery.setParameter("name",username);
        return typedQuery.getSingleResult();
    }

    @Override
    public void updateUserById(Long id, String name) {
        User user = em.find(User.class, id);
        user.setUsername(name);
        em.persist(user);
    }
}
