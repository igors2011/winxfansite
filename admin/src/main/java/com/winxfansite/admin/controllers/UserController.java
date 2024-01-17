package com.winxfansite.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class UserController {
    @GetMapping("/login")
    public String login(Model model) {
        return "/users/login";
    }
    @GetMapping("/authfail")
    public String authFail(Model model) {
        return "users/authfail";
    }
}
