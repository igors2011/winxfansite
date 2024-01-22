package com.winxfansite.admin.controllers;

import idao.admin.IUserAccess;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private IUserAccess userAccess;
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
    @PostMapping("/usersbyrole")
    public String usersByRole(@RequestParam("role") String role, Model model) {
        var users = userAccess.getUsersByRole(role);
        model.addAttribute("role", role);
        model.addAttribute("users", users);
        return "users/usersbyrole";
    }
    @PostMapping("/delete")
    public String usersByRole(@RequestParam("id") int id, Model model) {
        User user = new User();
        user.setId(id);
        userAccess.deleteUser(user);
        return "redirect:/admin/users";
    }
}
