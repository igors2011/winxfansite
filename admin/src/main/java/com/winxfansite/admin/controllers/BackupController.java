package com.winxfansite.admin.controllers;

import com.winxfansite.admin.dao.BackupAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/backup")
public class BackupController {
    @Autowired
    private BackupAccess backupAccess;
    @GetMapping(value = {"", "/"})
    public String backupPage() {
        return "backup/backup";
    }
    @PostMapping("/articles")
    @ResponseBody
    public void downloadArticles(HttpServletResponse response) throws IOException {
        String content = backupAccess.getArticles();
        response.setContentType("text/plain; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=articles.txt");
        response.getWriter().write(content);
    }
    @PostMapping("/users")
    @ResponseBody
    public void downloadUsers(HttpServletResponse response) throws IOException {
        String content = backupAccess.getUsers();
        response.setContentType("text/plain; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=users.txt");
        response.getWriter().write(content);
    }
    @PostMapping("/logs")
    @ResponseBody
    public void downloadLogs(HttpServletResponse response) throws IOException {
        String content = backupAccess.getLogs();
        response.setContentType("text/plain; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=logs.txt");
        response.getWriter().write(content);
    }
    @PostMapping("/comments")
    @ResponseBody
    public void downloadComments(HttpServletResponse response) throws IOException {
        String content = backupAccess.getComments();
        response.setContentType("text/plain; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=comments.txt");
        response.getWriter().write(content);
    }
}
