package ru.itmentor.spring.boot_security.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}", produces = "application/json")
    public User getUser(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @PostMapping("/register")
    public @ResponseBody User addUser(@RequestBody User user) {
        if (user.getUsername().equals("admin") && user.getPassword().equals("admin")){
            user.setRoles(List.of(new Role("USER"),new Role("ADMIN")));
        }else user.setRoles(List.of(new Role("USER")));
        userService.save(user);
        return user;
    }

    @PutMapping("/{id}")
    public User editUser(@PathVariable("id")Long id,String name)
    {
        userService.updateUserById(id,name);
        return userService.findUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id)
    {
        User userById = userService.findUserById(id);
        userService.delete(userById.getId());
    }
}
