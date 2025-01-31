package com.jagadeesh.registrationwebsite.controller;

import com.jagadeesh.registrationwebsite.dto.LoginDto;
import com.jagadeesh.registrationwebsite.entity.User;
import com.jagadeesh.registrationwebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView displayRegistrationForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("registrationForm");
        return modelAndView;
    }

    @PostMapping("/registered")
    public ModelAndView saveRegisteredUser(User user) {

        ModelAndView modelAndView = new ModelAndView();
        try {
            userService.saveUser(user);
            modelAndView.addObject("message", "Registration Successful !!!");
        } catch (RuntimeException exp) {
            modelAndView.addObject("message", "Registration Failed !!! " + exp.getMessage());
        }
        modelAndView.setViewName("registrationSuccessOrFail");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView displayLoginForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new LoginDto());
        modelAndView.setViewName("loginForm");
        return modelAndView;
    }

    @PostMapping("/loggedIn")
    public ModelAndView loginUser(User user) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            User userByUserEmailAndUserPassword = userService.findUserByUserEmailAndUserPassword(user.getUserEmail(), user.getUserPassword());
            modelAndView.addObject("successMessage", "Welcome "+userByUserEmailAndUserPassword.getUserName());
        } catch (RuntimeException exp) {
            modelAndView.addObject("errorMessage", "Login Failed. Email and password are incorrect");
        }
        modelAndView.setViewName("dashboard");
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logoutUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("registrationForm");
        return modelAndView;
    }
}
