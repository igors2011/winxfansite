package com.winxfansite.fansite.controllers;

import com.winxfansite.fansite.daopostgres.ArticleAccess;
import models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticlesController {
    private final ArticleAccess articleAccess;
    @Autowired
    public ArticlesController(ArticleAccess fairiesAccess) {
        this.articleAccess = fairiesAccess;
    }
    @GetMapping("/fairies")
    public String fairies(Model model) {
        List<Article> allFairies = articleAccess.getArticlesByType("Fairy");
        model.addAttribute("allFairies", allFairies);
        return "articles/fairies";
    }
    @GetMapping("/schools")
    public String schools(Model model) {
        List<Article> allSchools = articleAccess.getArticlesByType("School");
        model.addAttribute("allSchools", allSchools);
        return "articles/schools";
    }
    @GetMapping("/fairies/fairy/{id}")
    public String fairy(@PathVariable("id") int id, Model model) {
        Article article = articleAccess.getArticleById(id);
        model.addAttribute("article", article);
        return "articles/fairy";
    }
    @GetMapping("/schools/school/{id}")
    public String school(@PathVariable("id") int id, Model model) {
        Article article = articleAccess.getArticleById(id);
        model.addAttribute("article", article);
        return "articles/school";
    }
}
