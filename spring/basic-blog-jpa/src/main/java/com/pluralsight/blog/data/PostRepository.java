package com.pluralsight.blog.data;

import com.pluralsight.blog.model.Category;
import com.pluralsight.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.*;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByCategory(Category category);

}
