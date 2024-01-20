package com.winxfansite.admin.controllers;

import com.winxfansite.admin.dao.UserAccess;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserAccess userAccess;
    @GetMapping("/edit/{userId}")
    public String editUser(@PathVariable("userId") int userId, Model model) {
        User user = userAccess.getUserById(userId);
        model.addAttribute("user", user);
        return "/users/edituser";
    }
    @PostMapping("/edit")
    public String userUpdate(@ModelAttribute("user") User user) {
        userAccess.updateUser(user);
        return "redirect:/admin/users";
    }
    @GetMapping("/create")
    public String userCreate(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "/users/createuser";
    }
    @PostMapping("/create")
    public String userCreate(@ModelAttribute("user") User user) {
        userAccess.createUser(user);
        return "redirect:/admin/users";
    }
}
