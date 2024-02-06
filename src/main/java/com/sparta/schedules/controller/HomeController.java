package com.sparta.schedules.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("username", "username");
        return "index";
    }
    @GetMapping("/api/user/comment-page")
    public String comment(Model model) {
        model.addAttribute("username", "username");
        return "comment";
    }
    @GetMapping("/api/user/search-page")
    public String searchPage(Model model) {
        model.addAttribute("username", "username");
        return "search";
    }
}