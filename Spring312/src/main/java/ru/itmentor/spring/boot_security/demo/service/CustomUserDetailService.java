package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    public CustomUserDetailService(){}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byName = userService.findByUsername(username);
        if (byName == null){
            throw new UsernameNotFoundException("Пользователь не найден\nПроверьте правильность введённых данных");
        }
        if (!byName.getPassword().equals(byName.getPassword())) {
            throw new BadCredentialsException("Проверьте валидность пароля");
        }
         return new User(byName.getRoles(),
                 byName.getPassword(),
                byName.getUsername(),
                byName.isAccountNonExpired(),
                byName.isAccountNonLocked(),
                byName.isCredentialsNonExpired(),
                byName.isEnabled());
    }

}
