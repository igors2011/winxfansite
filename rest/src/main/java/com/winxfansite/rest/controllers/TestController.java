package com.winxfansite.rest.controllers;

import com.winxfansite.rest.daopostgres.ArticleAccess;
import models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
public class TestController {

    @Autowired
    private final ArticleAccess articleAccess;
    public TestController(ArticleAccess articleAccess) {
        this.articleAccess = articleAccess;
    }
    @GetMapping(value = {"", "/"})
    public String helloWorld() {
        return "Hello world!";
    }
    @GetMapping("fairies")
    public ResponseEntity<List<Article>> fairies() {
        List<Article> fairies = articleAccess.getArticlesByType("fairies");
        return new ResponseEntity<>(fairies, HttpStatus.OK);
    }
    @GetMapping("/fairies/{fairyName}")
    public ResponseEntity<Article> getFairyByName(@PathVariable("fairyName") String fairyName) {
        Article fairy = articleAccess.getArticleByHeader(fairyName);
        return new ResponseEntity<>(fairy, HttpStatus.OK);
    }
}
