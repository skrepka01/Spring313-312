package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    private UserService userService;

    public AdminController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/admin/home")
    public String home()
    {
        return "home";
    }

    @GetMapping("/admin/edit")
    public String editor()
    {
        return "edit";
    }

    @PostMapping("/admin/edit")
    public String editor(@RequestParam(name = "id") Long id,String name,
                         String role)
    {
        User userById = userService.findUserById(id);
        userById.setUsername(name);
        userById.setRoles(List.of(new Role(role)));
        userService.updateUserById(id,name);
        return "redirect:/profile";
    }

    @GetMapping("/admin/remove")
    public String remove()
    {
        return "remove";
    }

    @PostMapping("/admin/remove")
    public String remove(@RequestParam(name = "id")Long id)
    {
        userService.delete(userService.findUserById(id).getId());
        return "redirect:/profile";
    }
}
