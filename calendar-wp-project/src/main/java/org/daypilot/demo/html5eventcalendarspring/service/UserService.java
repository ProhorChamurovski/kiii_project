package org.daypilot.demo.html5eventcalendarspring.service;


import org.daypilot.demo.html5eventcalendarspring.model.User;

public interface UserService {

    User login(String username, String password);
    User register(String username, String password, String repeatPassowrd, String name, String surname);

}
