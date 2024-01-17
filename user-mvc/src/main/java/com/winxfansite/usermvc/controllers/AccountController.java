package com.winxfansite.usermvc.controllers;

import models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountController {
    @GetMapping("/login")
    public String login() {
        return "users/login";
    }
}
