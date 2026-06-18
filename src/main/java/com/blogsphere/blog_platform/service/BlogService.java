package com.blogsphere.blog_platform.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogsphere.blog_platform.entity.BlogPost;
import com.blogsphere.blog_platform.repository.BlogRepository;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public BlogPost saveBlog(BlogPost blogPost) {
        return blogRepository.save(blogPost);
    }

    public List<BlogPost> getAllBlogs() {
        return blogRepository.findAll();
    }

    public BlogPost getBlogById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }
    public void deleteBlog(Long id) {
    blogRepository.deleteById(id);
}
public List<BlogPost> searchBlogs(String keyword) {

    return blogRepository.findByTitleContainingIgnoreCase(keyword);

}
public void likeBlog(Long id) {

    BlogPost blog = blogRepository
            .findById(id)
            .orElse(null);

    if (blog != null) {

        Integer currentLikes = blog.getLikes();

        if (currentLikes == null) {
            currentLikes = 0;
        }

        blog.setLikes(currentLikes + 1);

        blogRepository.save(blog);
    }
}
}