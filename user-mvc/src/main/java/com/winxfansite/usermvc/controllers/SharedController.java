package com.winxfansite.usermvc.controllers;

import com.winxfansite.usermvc.daopostgres.ArticleAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Base64;

@Controller
@RequestMapping("/shared")
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
        String viewCategory = switch (category) {
            case "fairies" -> "Феи";
            case "specialists" -> "Специалисты";
            case "villains" -> "Злодеи";
            case "schools" -> "Школы";
            default -> "";
        };
        model.addAttribute("category", viewCategory);
        return "articles/category";
    }
    @GetMapping("articles/{articleName}")
    public String article(@PathVariable("articleName") String articleName, Model model) {
        var article = articleAccess.getArticleByHeader(articleName);
        byte[] imageData = articleAccess.getImageByArticleId(article.getId());
        if (imageData != null) {
            String base64Image = Base64.getEncoder().encodeToString(imageData);
            model.addAttribute("image", base64Image);
        }
        articleAccess.increaseVisits(article);
        model.addAttribute("article", article);
        return "articles/article";
    }
    @GetMapping("/viewimage/{imageId}")
    public ResponseEntity<byte[]> viewImage(@PathVariable int articleId) {
        byte[] img = articleAccess.getImageByArticleId(articleId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(img, headers, HttpStatus.OK);
    }
}
