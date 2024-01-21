package com.winxfansite.usermvc.controllers;

import com.winxfansite.usermvc.daopostgres.ArticleAccess;
import models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/editor")
public class EditorController {
    @Autowired
    private ArticleAccess articleAccess;
    @GetMapping("/add")
    public String addArticle(Model model) {
        model.addAttribute("article", new Article());
        return "articles/newarticle";
    }
    @PostMapping("/add")
    public String addArticle(@ModelAttribute("article") Article article, @RequestParam("image") MultipartFile file) {
        try {
            byte[] imageData = file.getBytes();
            articleAccess.insertOrUpdateArticle(article, imageData, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    public String editArticle(@ModelAttribute("article") Article article, @RequestParam("image") MultipartFile file) {
        try {
            byte[] imageData = file.getBytes();
            articleAccess.insertOrUpdateArticle(article, imageData, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "articles/editarticlesuccess";
    }
    @PostMapping("/delete")
    public String deleteArticle(@ModelAttribute("article") Article article) {
        articleAccess.deleteArticle(article.getId());
        return "articles/deletearticlesuccess";
    }
    @PostMapping("/deletecomment")
    public String deleteComment(@RequestParam("commentId") int commentId, HttpServletRequest request) {
        articleAccess.deleteCommentById(commentId);
        String referer = request.getHeader("referer");
        return "redirect:" + referer;
    }
}
