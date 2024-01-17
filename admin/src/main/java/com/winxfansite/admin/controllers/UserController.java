package com.winxfansite.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class UserController {
    @GetMapping(value = {"", "/"})
    public String defPage() {
        return "redirect:/success";
    }
    @GetMapping("/login")
    public String login(Model model) {
        return "users/login";
    }
    @GetMapping("/success")
    public String authSuccess() {
        return "users/success";
    }
    @GetMapping("/authfail")
    public String authFail(Model model) {
        return "users/authfail";
    }
    @GetMapping("/error")
    public String error(Model model) {
        return "errors/error";
    }
}
