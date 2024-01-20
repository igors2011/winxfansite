package com.winxfansite.usermvc.controllers;

import com.winxfansite.usermvc.daopostgres.UserAccess;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
    @GetMapping("/edit")
    public String edit(Model model, Principal principal) {
        String userName = principal.getName();
        User user = userAccess.getUserByName(userName);
        model.addAttribute("user", user);
        return "/users/edit";
    }
    @PostMapping("/edit")
    public String updateUserProfile(@ModelAttribute("user") User user, @RequestParam("newUsername") String newUsername, Principal principal) {
        String username = principal.getName();
        if (username.equals(user.getUsername())) {
            userAccess.editUser(user, newUsername);
        }
        return "redirect:/logout";
    }
}
