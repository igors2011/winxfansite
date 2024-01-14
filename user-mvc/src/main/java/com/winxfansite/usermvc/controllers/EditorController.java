package com.winxfansite.usermvc.controllers;

import com.winxfansite.usermvc.daopostgres.ArticleAccess;
import models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/editor")
public class EditorController {
    private final ArticleAccess articleAccess;
    @Autowired
    public EditorController(ArticleAccess fairiesAccess) {
        this.articleAccess = fairiesAccess;
    }
    @GetMapping("/add")
    public String addArticle(Model model) {
        model.addAttribute("article", new Article());
        return "articles/newarticle";
    }
    @PostMapping("/add")
    public String addArticle(@ModelAttribute("article") Article article) {
        articleAccess.insertArticle(article);
        return "articles/newarticlesuccess";
    }
}
