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
@RequestMapping("fairies")
public class FairiesController {
    @Autowired
    private final ArticleAccess articleAccess;

    public FairiesController(ArticleAccess articleAccess) {
        this.articleAccess = articleAccess;
    }
    @GetMapping(value = {"", "/"})
    public String getFairies(Model model) {
        List<Article> allFairies = articleAccess.getArticles("Fairy");
        model.addAttribute("allFairies", allFairies);
        return "articles/fairies";
    }
    @GetMapping("{fairyName}")
    public String getFairyByName(@PathVariable("fairyName") String fairyName, Model model) {
        Article fairy = articleAccess.getArticleByHeader(fairyName);
        model.addAttribute("fairy", fairy);
        return "articles/fairy";
    }
}
