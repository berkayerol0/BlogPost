package com.example.blogpost.berkayerol.repository;

import com.example.blogpost.berkayerol.model.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogRepository extends MongoRepository<Blog,String> {

    Optional<Blog> findByID(String ID);

    Optional<Blog> findByUserID(String userID);
}
