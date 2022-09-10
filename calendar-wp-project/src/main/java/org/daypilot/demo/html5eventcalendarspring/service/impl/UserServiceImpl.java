package org.daypilot.demo.html5eventcalendarspring.service.impl;


import org.daypilot.demo.html5eventcalendarspring.model.User;
import org.daypilot.demo.html5eventcalendarspring.model.exceptions.InvalidUserArgumentException;
import org.daypilot.demo.html5eventcalendarspring.model.exceptions.PasswordMismatchException;
import org.daypilot.demo.html5eventcalendarspring.model.exceptions.UsernameAlreadyExistsException;
import org.daypilot.demo.html5eventcalendarspring.repository.UserRepository;
import org.daypilot.demo.html5eventcalendarspring.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidUserArgumentException();
        }
        return this.userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public User register(String username, String password, String repeatPassowrd, String name, String surname) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidUserArgumentException();
        }
        if (!password.equals(repeatPassowrd)) {
            throw new PasswordMismatchException();
        }
        if (this.userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException();
        }
        String encryptedPassword = passwordEncoder.encode(password);
        User user = new User(username, encryptedPassword, name, surname);
        return this.userRepository.save(user);
    }
}


