package com.winxfansite.fansite.controllers;

import com.winxfansite.fansite.daopostgres.ArticleAccess;
import com.winxfansite.fansite.models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("schools")
public class SchoolsController {
    @Autowired
    private final ArticleAccess articleAccess;
    public SchoolsController(ArticleAccess articleAccess) {
        this.articleAccess = articleAccess;
    }
    @GetMapping(value = {"", "/"})
    public String getSchools(Model model) {
        List<Article> allSchools = articleAccess.getArticlesByType("schools");
        model.addAttribute("allSchools", allSchools);
        return "articles/schools";
    }
    @GetMapping("/{schoolName}")
    public String getSchoolByName(@PathVariable("schoolName") String schoolName, Model model) {
        Article school = articleAccess.getArticleByHeader(schoolName);
        model.addAttribute("school", school);
        return "articles/school";
    }
}
