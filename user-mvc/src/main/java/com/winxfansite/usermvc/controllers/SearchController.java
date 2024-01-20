package com.winxfansite.usermvc.controllers;

import com.winxfansite.usermvc.daopostgres.SearchAccess;
import models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private final SearchAccess searchAccess;

    public SearchController(SearchAccess searchAccess) {
        this.searchAccess = searchAccess;
    }

    @GetMapping("")
    public String searchResult(@RequestParam("query") String query, Model model)
    {
        List<Article> foundArticles = searchAccess.find(query);
        if (foundArticles.isEmpty()) {
            model.addAttribute("message", "К сожалению, по запросу " + query + " ничего не нашлось");
        }
        else {
            model.addAttribute("message", "Список статей, найденных по запросу " + query + ":");
        }
        model.addAttribute("searchResult", foundArticles);
        return "search/searchResult";
    }
}
