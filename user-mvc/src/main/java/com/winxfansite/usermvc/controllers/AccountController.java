package com.winxfansite.usermvc.controllers;

import models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountController {
    @GetMapping("/login")
    public String login(Model model) {
        User user = new User();
        user.setLogin("useruseruser");
        user.setPassword("12345");
        model.addAttribute("user", user);
        return "account/login";
    }
}
