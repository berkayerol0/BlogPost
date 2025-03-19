package com.example.blogpost.berkayerol.repository;

import com.example.blogpost.berkayerol.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
