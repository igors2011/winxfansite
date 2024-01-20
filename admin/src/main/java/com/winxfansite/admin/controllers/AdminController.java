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

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ArticleAccess articleAccess;
    @Autowired
    private LogAccess logAccess;
    @Autowired
    private UserAccess userAccess;
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
    public String logsByType(@RequestParam("type") String type, Model model, @RequestParam("dateFrom") String dateFrom, @RequestParam("dateTo") String dateTo) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date parsedDateFrom = dateFormat.parse(dateFrom);
        Date parsedDateTo = dateFormat.parse(dateTo);
        Timestamp dFrom = new Timestamp(parsedDateFrom.getTime());
        Timestamp dTo = new Timestamp(parsedDateTo.getTime());
        var logsByType = logAccess.getLogsByType(type, dFrom, dTo);
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
