package com.example.blogpost.berkayerol.controller;

import com.example.blogpost.berkayerol.model.Comment;
import com.example.blogpost.berkayerol.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v3/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/add")
    public ResponseEntity<?> addComment(@RequestParam String blogPostID, @RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.addCommentToBlog(blogPostID, comment));
    }

    @GetMapping("/edit")
    public ResponseEntity<?> editComment(@RequestParam String commentID, @RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.editCommentToBlog(commentID, comment));
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deleteComment(@RequestParam String commentID, @RequestParam String blogID) {
        return ResponseEntity.ok(commentService.deleteCommentToBlog(commentID, blogID));
    }

    @GetMapping("/commentsByBlogID")
    public ResponseEntity<?> getCommentsByBlogID(@RequestParam String blogPostID, @PageableDefault(size = 10) Pageable pageable) {
        Page<Comment> comments = commentService.returnCommentsByBlogID(blogPostID, pageable);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/commentsByUserID")
    public ResponseEntity<?> returnCommentsByUserID(@RequestParam String userID, @PageableDefault(size=10) Pageable pageable) {
        Page<Comment> comments = commentService.returnCommentsByUserID(userID, pageable);
        return ResponseEntity.ok(comments);
    }
}
