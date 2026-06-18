package com.blogsphere.blog_platform.controller;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.blogsphere.blog_platform.entity.BlogPost;
import com.blogsphere.blog_platform.service.BlogService;

@Controller
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {

        model.addAttribute("blogPost", new BlogPost());

        return "create-post";
    }

   @PostMapping("/save")
public String saveBlog(
        @ModelAttribute BlogPost blogPost,
        MultipartFile imageFile
) throws IOException {

    if (!imageFile.isEmpty()) {

        String fileName =
                UUID.randomUUID() + "_" +
                imageFile.getOriginalFilename();

        String uploadDir =
        System.getProperty("user.dir")
        + File.separator
        + "uploads";

       File directory = new File(uploadDir);

if (!directory.exists()) {
    directory.mkdirs();
}

File destinationFile =
        new File(directory, fileName);

imageFile.transferTo(destinationFile);

        blogPost.setImageName(fileName);
    }

    blogService.saveBlog(blogPost);

    return "redirect:/";
}
    @GetMapping("/blog/{id}")
public String viewBlog(@PathVariable Long id, Model model) {

    model.addAttribute(
            "blog",
            blogService.getBlogById(id)
    );

    return "blog-details";
}
@GetMapping("/delete/{id}")
public String deleteBlog(@PathVariable Long id) {

    blogService.deleteBlog(id);

    return "redirect:/";
}
@GetMapping("/edit/{id}")
public String editBlog(@PathVariable Long id, Model model) {

    BlogPost blog = blogService.getBlogById(id);

    model.addAttribute("blogPost", blog);

    return "edit-post";
}

@PostMapping("/update")
public String updateBlog(@ModelAttribute BlogPost blogPost) {

    blogService.saveBlog(blogPost);

    return "redirect:/";
}
@GetMapping("/like/{id}")
public String likeBlog(@PathVariable Long id) {

    blogService.likeBlog(id);

    return "redirect:/blog/" + id;
}
}