package com.winxfansite.fansite.controllers;

import com.winxfansite.fansite.dataaccesspostgres.ArticleAccess;
import com.winxfansite.fansite.models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping()
    public String index(Model model) {
        return "main/index";
    }
}
