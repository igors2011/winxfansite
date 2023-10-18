package com.winxfansite.fansite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(Model model)
    {
        model.addAttribute("title", "Добро пожаловать на фан-сайт Винкс!");
        model.addAttribute("content", "Hello world!");
        return "index";
    }
}
