package com.winxfansite.usermvc.controllers;

import com.winxfansite.usermvc.daopostgres.UserAccess;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountController {
    private final UserAccess userAccess;
    @Autowired
    public AccountController(UserAccess userAccess) {
        this.userAccess = userAccess;
    }
    @GetMapping("/login")
    public String login() {
        return "users/login";
    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("newUser", new User());
        return "users/register";
    }
    @PostMapping("/register")
    public String addUser(@ModelAttribute("newUser") User newUser) {
        userAccess.insertUser(newUser);
        return "redirect:/account/login";
    }
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/account/login";
    }
}
