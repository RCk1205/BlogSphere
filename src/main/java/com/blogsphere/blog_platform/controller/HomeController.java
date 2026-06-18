package com.blogsphere.blog_platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blogsphere.blog_platform.service.BlogService;

@Controller
public class HomeController {

    private final BlogService blogService;

    public HomeController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/")
public String home(
        @RequestParam(required = false) String keyword,
        Model model) {

    if (keyword != null && !keyword.isEmpty()) {

        model.addAttribute(
                "blogs",
                blogService.searchBlogs(keyword)
        );

    } else {

        model.addAttribute(
                "blogs",
                blogService.getAllBlogs()
        );

    }

    return "index";
}
}