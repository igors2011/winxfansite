package com.winxfansite.usermvc.controllers;

import com.winxfansite.usermvc.daopostgres.ArticleAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping()
public class SharedController {
    private final ArticleAccess articleAccess;
    @Autowired
    public SharedController(ArticleAccess fairiesAccess) {
        this.articleAccess = fairiesAccess;
    }
    @GetMapping(value = {"", "/"})
    public String index() {
        return "main/index";
    }
    @GetMapping("articles")
    public String articles(Model model) {
        var allArticles = articleAccess.getAllArticles();
        model.addAttribute("allArticles", allArticles);
        return "articles/articles";
    }
    @GetMapping("{category}")
    public String category(@PathVariable("category") String category, Model model) {
        var articlesByCategory = articleAccess.getArticlesByType(category);
        model.addAttribute("articlesByCategory", articlesByCategory);
        model.addAttribute("category", category);
        return "articles/category";
    }
    @GetMapping("articles/{articleName}")
    public String article(@PathVariable("articleName") String articleName, Model model) {
        var article = articleAccess.getArticleByHeader(articleName);
        articleAccess.increaseVisits(article);
        model.addAttribute("article", article);
        return "articles/article";
    }
}
