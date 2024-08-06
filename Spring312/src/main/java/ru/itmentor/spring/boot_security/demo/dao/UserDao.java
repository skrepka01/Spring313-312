package ru.itmentor.spring.boot_security.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao{

    //User findById(Long id);
    User findUserById(Long id);

    User save(User entity);

    void delete(Long id);

    User findByUsername(String username);

    void updateUserById(Long id,String name);
    /*    User save(User user);
    User findByUsername(String username);

    void removeUser(Long id);

    void updateUser(Long id, String name, String role);

    List<User> findAll();*/

}
