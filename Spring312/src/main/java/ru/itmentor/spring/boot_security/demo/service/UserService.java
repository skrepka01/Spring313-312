package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService{

    User findUserById(Long id);

    User save(User entity);

    void delete(Long id);

    User findByUsername(String username);

    void updateUserById(Long id,String name);
}
