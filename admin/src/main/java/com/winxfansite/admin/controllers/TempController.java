package com.winxfansite.admin.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/temp")
public class TempController {

    @GetMapping(value = {"", "/"})
    public String role(Authentication authentication) {
        return "test/test";
    }
}
