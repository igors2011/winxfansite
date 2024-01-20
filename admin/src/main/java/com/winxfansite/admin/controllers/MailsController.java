package com.winxfansite.admin.controllers;

import com.winxfansite.admin.dao.MailAccess;
import com.winxfansite.admin.dao.UserAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mails")
public class MailsController {
    private final UserAccess userAccess;
    private final MailAccess mailAccess;
    @Autowired
    public MailsController(UserAccess userAccess, MailAccess mailAccess) {
        this.userAccess = userAccess;
        this.mailAccess = mailAccess;
    }
    @GetMapping(value = {"", "/"})
    public String mails() {
        return "mails/mails";
    }
    @PostMapping("")
    public String sendMail(@RequestParam("subject") String subject, @RequestParam("message") String message) {
        var mailList = userAccess.getUserEmails();
        try {
            mailAccess.sendEmail(mailList, subject, message);
        }
        catch (Exception e) {
            return "errors/error";
        }
        return "redirect:/mails/success";
    }
    @GetMapping("/success")
    public String sentMail() {
        return "mails/success";
    }
}
