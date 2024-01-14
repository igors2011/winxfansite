package com.winxfansite.usermvc.controllers;

import com.winxfansite.usermvc.daopostgres.ArticleAccess;
import models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        articleAccess.insertOrUpdateArticle(article, false);
        return "articles/newarticlesuccess";
    }
    @GetMapping("/edit/{articleName}")
    public String editArticle(@PathVariable("articleName") String articleName, Model model)
    {
        Article article = articleAccess.getArticleByHeader(articleName);
        model.addAttribute("article", article);
        return "articles/editarticle";
    }
    @PostMapping("/edit")
    public String editArticle(@ModelAttribute("article") Article article) {
        articleAccess.insertOrUpdateArticle(article, true);
        return "articles/editarticlesuccess";
    }
    @PostMapping("/delete")
    public String deleteArticle(@ModelAttribute("article") Article article) {
        articleAccess.deleteArticle(article.getId());
        return "articles/deletearticlesuccess";
    }
}
