package com.example.blogpost.berkayerol.controller;

import com.example.blogpost.berkayerol.dto.AddRequest;
import com.example.blogpost.berkayerol.dto.EditRequest;
import com.example.blogpost.berkayerol.dto.LikeRequest;
import com.example.blogpost.berkayerol.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v2/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/add")
    public ResponseEntity<?> add(@RequestBody AddRequest addRequest) {
        return ResponseEntity.ok(blogService.add(addRequest));
    }

    @GetMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody EditRequest editRequest) {
        return ResponseEntity.ok(blogService.edit(editRequest));
    }

    @GetMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam String blogID) {
        return ResponseEntity.ok(blogService.delete(blogID));
    }

    @GetMapping("/likeList")
    public ResponseEntity<?> likedUsers(@RequestBody LikeRequest likeRequest) {
        List<String> likedUsersList = blogService.likedUsers(likeRequest.getBlogID());
        return ResponseEntity.ok(likedUsersList);
    }

    @GetMapping("/likePost")
    public ResponseEntity<?> likePost(@RequestParam String blogID) {
        return ResponseEntity.ok(blogService.likePost(blogID));
    }

    @GetMapping("/dislikePost")
    public ResponseEntity<?> dislikePost(@RequestParam String blogID) {
        return ResponseEntity.ok(blogService.dislikePost(blogID));
    }

    @GetMapping("/removeLike")
    public ResponseEntity<?> removeLike(@RequestParam String blogID) {
        return ResponseEntity.ok(blogService.removeLike(blogID));
    }

    @GetMapping("/removeDislike")
    public ResponseEntity<?> removeDislike(@RequestParam String blogID) {
        return ResponseEntity.ok(blogService.removeDislike(blogID));
    }
}
