package com.winxfansite.usermvc.controllers;

import com.winxfansite.usermvc.daopostgres.ArticleAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Base64;

@Controller
@RequestMapping("/image")
public class ImageController {
    private final ArticleAccess articleAccess;
    @Autowired
    public ImageController(ArticleAccess fairiesAccess) {
        this.articleAccess = fairiesAccess;
    }
    @GetMapping("/image/{id}")
    public String getImage(@PathVariable int articleId, Model model) {
        byte[] imageData = articleAccess.getImageByArticleId(articleId);
        String base64Image = Base64.getEncoder().encodeToString(imageData);
        model.addAttribute("image", base64Image);
        return "image-view";
    }
}
