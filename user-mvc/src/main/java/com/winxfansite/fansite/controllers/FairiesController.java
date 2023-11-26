package com.winxfansite.fansite.controllers;

import com.winxfansite.fansite.daopostgres.ArticleAccess;
import com.winxfansite.fansite.rest.RestConnection;
import models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("fairies")
public class FairiesController {
    @Autowired
    private final ArticleAccess articleAccess;
    private final RestTemplate restTemplate;

    public FairiesController(ArticleAccess articleAccess) {
        this.articleAccess = articleAccess;
        this.restTemplate = new RestTemplate();
    }
    @GetMapping(value = {"", "/"})
    public String getFairies(Model model) {
        String url = RestConnection.getRestURL() + "/fairies";

        ResponseEntity<ArrayList<Article>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<Article>>() {
        });

        ArrayList<Article> allFairies = response.getBody();

        model.addAttribute("allFairies", allFairies);
        return "articles/fairies";
    }
    @GetMapping("{fairyName}")
    public String getFairyByName(@PathVariable("fairyName") String fairyName, Model model) {
        String url = RestConnection.getRestURL() + "/fairies/" + fairyName;
        ResponseEntity<Article> response = restTemplate.getForEntity(url, Article.class);

        Article fairy = response.getBody();
        model.addAttribute("fairy", fairy);
        return "articles/fairy";
    }
}
