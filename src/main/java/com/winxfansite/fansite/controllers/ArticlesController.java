package com.winxfansite.fansite.controllers;

import com.winxfansite.fansite.dataaccesspostgres.ArticleAccess;
import com.winxfansite.fansite.models.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticlesController {
    private final ArticleAccess articleAccess;
    public ArticlesController(ArticleAccess fairiesAccess) {
        this.articleAccess = fairiesAccess;
    }
    @GetMapping("/fairies")
    public String fairies(Model model) {
        List<Article> allFairies = articleAccess.getArticles("Fairy");
        model.addAttribute("allFairies", allFairies);
        return "articles/fairies/fairies";
    }
    @GetMapping("/schools")
    public String schools(Model model) {
        List<Article> allSchools = articleAccess.getArticles("School");
        model.addAttribute("allSchools", allSchools);
        return "articles/schools/schools";
    }
    @GetMapping("/fairies/fairy/{id}")
    public String fairy(@PathVariable("id") int id, Model model) {
        Article article = articleAccess.getArticleById(id);
        model.addAttribute("article", article);
        return "articles/fairies/fairy";
    }
    @GetMapping("/schools/school/{id}")
    public String school(@PathVariable("id") int id, Model model) {
        Article article = articleAccess.getArticleById(id);
        model.addAttribute("article", article);
        return "articles/schools/school";
    }



    @GetMapping("/search")
    public String searchResult(@RequestParam("query") String query, Model model)
    {
        //Добавить результаты поиска
        return "articles/searchresult";
    }
}
