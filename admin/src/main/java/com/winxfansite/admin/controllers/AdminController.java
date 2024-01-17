package com.winxfansite.admin.controllers;

import com.winxfansite.admin.dao.ArticleAccess;
import com.winxfansite.admin.dao.LogAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping()
public class AdminController {
    private final ArticleAccess articleAccess;
    private final LogAccess logAccess;
    @Autowired
    public AdminController(ArticleAccess articleAccess, LogAccess logAccess) {
        this.articleAccess = articleAccess;
        this.logAccess = logAccess;
    }
    @GetMapping(value = {"", "/"})
    public String articles(Model model) {
        var allArticles = articleAccess.getAllArticles();
        model.addAttribute("allArticles", allArticles);
        return "articles/articles";
    }
    @GetMapping("/logs")
    public String logs(Model model) {
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
}
