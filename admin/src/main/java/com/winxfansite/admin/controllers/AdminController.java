package com.winxfansite.admin.controllers;

import com.winxfansite.admin.dao.ArticleAccess;
import com.winxfansite.admin.dao.LogAccess;
import com.winxfansite.admin.dao.UserAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ArticleAccess articleAccess;
    private final LogAccess logAccess;
    private final UserAccess userAccess;
    @Autowired
    public AdminController(ArticleAccess articleAccess, LogAccess logAccess, UserAccess userAccess) {
        this.articleAccess = articleAccess;
        this.logAccess = logAccess;
        this.userAccess = userAccess;
    }
    @GetMapping(value = {"", "/", "/articles"})
    public String articles(Model model) {
        var allArticles = articleAccess.getAllArticles();
        model.addAttribute("allArticles", allArticles);
        return "articles/articles";
    }
    @GetMapping("/logs")
    public String logs(Authentication authentication, Model model) {
        var allLogs = logAccess.getAllLogs();
        model.addAttribute("allLogs", allLogs);
        return "logs/logs";
    }
    @GetMapping("/logs/type")
    public String logsByType(@RequestParam("type") String type, Model model) {
        var logsByType = logAccess.getLogsByType(type);
        model.addAttribute("type", type);
        model.addAttribute("logsByType", logsByType);
        return "logs/logsbytype";
    }
    @GetMapping("/users")
    public String users(Model model) {
        var allUsers = userAccess.getAllUsers();
        model.addAttribute("allUsers", allUsers);
        return "/users/users";
    }
}
