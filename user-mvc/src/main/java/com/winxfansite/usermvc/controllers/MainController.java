package com.winxfansite.usermvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class MainController {
    @GetMapping(value = {"", "/"})
    String mainRed() {
        return "redirect:/shared/";
    }
}
