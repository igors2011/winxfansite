package com.winxfansite.usermvc.controllers;

import com.winxfansite.usermvc.daopostgres.ArticleAccess;
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
        List<Article> allFairies = articleAccess.getArticlesByType("fairies");
        model.addAttribute("allFairies", allFairies);
        return "articles/fairies";
    }
    @GetMapping("/fairies/{fairyName}")
    public String fairy(@PathVariable("fairyName") String fairyName, Model model) {
        Article article = articleAccess.getArticleByHeader(fairyName);
        model.addAttribute("fairy", article);
        return "articles/fairy";
    }
}
