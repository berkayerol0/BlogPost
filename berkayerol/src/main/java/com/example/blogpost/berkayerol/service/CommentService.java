package com.example.blogpost.berkayerol.service;

import com.example.blogpost.berkayerol.model.Blog;
import com.example.blogpost.berkayerol.model.Comment;
import com.example.blogpost.berkayerol.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final BlogService blogService;

    public Comment addCommentToBlog(String blogID, Comment comment) {
        Blog blog = blogService.findBlogByID(blogID);
        comment.setBlogPostID(blogID);
        Comment savedComment = commentRepository.save(comment);
        blog.getCommentIDs().add(savedComment.getID());
        blogService.save(blog);
        return savedComment;
    }

    public Comment editCommentToBlog(String commentID, Comment editRequest) {
        Comment comment = commentRepository.findByID(commentID).orElseThrow();
        comment.setContext(editRequest.getContext());
        return commentRepository.save(comment);
    }

    public boolean deleteCommentToBlog(String commentID, String blogID) {
        commentRepository.deleteById(commentID);
        Blog blog = blogService.findBlogByID(blogID);
        blog.getCommentIDs().remove(commentID);
        blogService.save(blog);
        return true;
    }

    public Page<Comment> returnCommentsByBlogID(String blogPostID, Pageable pageable) {
        PageRequest.of(1,3);
        return commentRepository.findByBlogPostID(blogPostID, pageable);
    }

    public Page<Comment> returnCommentsByUserID(String userID, Pageable pageable) {
        PageRequest.of(1,3);
        return commentRepository.findByUserID(userID, pageable);
    }

}
