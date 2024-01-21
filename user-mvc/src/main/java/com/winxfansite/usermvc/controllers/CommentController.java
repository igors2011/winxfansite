package com.winxfansite.usermvc.controllers;

import com.winxfansite.usermvc.daopostgres.ArticleAccess;
import com.winxfansite.usermvc.daopostgres.UserAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private UserAccess userAccess;
    @Autowired
    private ArticleAccess articleAccess;
    @PostMapping(value = {"", "/"})
    public String writeComment(@RequestParam("articleId") int articleId, @RequestParam("message") String message, @RequestParam("articleName") String articleName, Authentication authentication, HttpServletRequest request) {
        int userId = userAccess.getUserIdByName(authentication.getName());
        articleAccess.addComment(userId, articleId, message);
        String referer = request.getHeader("referer");
        return "redirect:" + referer;
    }
}
