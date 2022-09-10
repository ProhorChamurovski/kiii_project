package org.daypilot.demo.html5eventcalendarspring.controller;


import org.daypilot.demo.html5eventcalendarspring.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegister(Model model){
        return "register";
    }

    @GetMapping("/login")
    public String showLogin(Model model){
        return "login";
    }


    @PostMapping("/register")
    public String createUser(@RequestParam String username,
                         @RequestParam String password,
                         @RequestParam String repeatPassword,
                         @RequestParam String name,
                         @RequestParam String surname) {
        this.userService.register(username, password, repeatPassword, name, surname);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username,
                         @RequestParam String password) {
        this.userService.login(username, password);
        return "redirect:/";
    }


}
