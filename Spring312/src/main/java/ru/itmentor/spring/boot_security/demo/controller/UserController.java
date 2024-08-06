package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user")
    public String myProfile(ModelMap model) {
        model.addAttribute("profile", "SSSS");
        return "/user";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam(name = "name") String name,
                         @RequestParam(name = "password")String password)
    {
        if (name.equals("admin") && password.equals("admin"))
        {
            User admin = new User(name, passwordEncoder.encode(password), List.of(new Role("USER"),new Role("ADMIN")));
            userService.save(admin);
        }else {
            User user = new User(name, passwordEncoder.encode(password), List.of(new Role("USER")));
            userService.save(user);
        }
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(ModelMap modelMap,Principal principal)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        modelMap.addAttribute("username","Имя пользователя: " + principal.getName());
        modelMap.addAttribute("roles", "Права пользователя:" + authentication.getAuthorities());
        return "/profile";
    }
}
