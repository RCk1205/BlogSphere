package com.blogsphere.blog_platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogsphere.blog_platform.entity.BlogPost;

public interface BlogRepository extends JpaRepository<BlogPost, Long> {

    List<BlogPost> findByTitleContainingIgnoreCase(String keyword);

}