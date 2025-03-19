package com.example.blogpost.berkayerol.repository;

import com.example.blogpost.berkayerol.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    Optional<Comment> findByID(String ID);

    Page<Comment> findByBlogPostID(String blogPostID, Pageable pageable);

    Page<Comment> findByUserID(String userID, Pageable pageable);
}
