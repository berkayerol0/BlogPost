package com.example.blogpost.berkayerol.service;

import com.example.blogpost.berkayerol.dto.AddRequest;
import com.example.blogpost.berkayerol.dto.EditRequest;
import com.example.blogpost.berkayerol.model.Blog;
import com.example.blogpost.berkayerol.model.User;
import com.example.blogpost.berkayerol.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    private final AuthenticationService authenticationService;

    public Blog add(AddRequest addRequest) {
        String loggedInUser =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        Blog blog = new Blog();
        blog.setTitle(addRequest.getTitle());
        blog.setContext(addRequest.getContext());
        blog.setTime(LocalDateTime.now());
        blog.setCommentIDs(new ArrayList<>());
        blog.setLikeIDs(new ArrayList<>());
        blog.setDislikeIDs(new ArrayList<>());
        blog.setLikeCount(0);

        User user = authenticationService.findUserByMail(loggedInUser);
        blog.setUserID(user.getID());
        blogRepository.save(blog);
        return blog;
    }

    public Blog edit(EditRequest editRequest) {
        Blog blog = blogRepository.findByID(editRequest.getBlogID()).orElseThrow();
        blog.setTitle(editRequest.getTitle());
        blog.setContext(editRequest.getContext());
        blog.setTime(editRequest.getTime());
        blogRepository.save(blog);
        return blog;
    }

    public boolean delete(String blogID) {
        blogRepository.deleteById(blogID);
        return true;
    }

    public Blog findBlogByID(String ID) {
        Optional<Blog> blog = blogRepository.findByID(ID);

        if(blog.isPresent()) {
            return blog.get();
        }

        return null;
    }

    public static String currentID() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getID();
    }

    public Blog likePost(String blogID) {
        String loggedInUser = currentID();
        Blog blog = blogRepository.findByID(blogID).orElseThrow();

        if(blog.getDislikeIDs().contains(loggedInUser)) {
            blog.getDislikeIDs().remove(loggedInUser);
            blog.setDislikeCount(blog.getDislikeCount() - 1);
        }

        if(!blog.getLikeIDs().contains(loggedInUser)) {
            blog.getLikeIDs().add(loggedInUser);
            blog.setLikeCount(blog.getLikeCount() + 1);
        }
        blogRepository.save(blog);
        return blog;
    }

    public Blog dislikePost(String blogID) {
        String loggedInUser = currentID();
        Blog blog = blogRepository.findByID(blogID).orElseThrow();

        if(blog.getLikeIDs().contains(loggedInUser)) {
            blog.getLikeIDs().remove(loggedInUser);
            blog.setLikeCount(blog.getLikeCount() - 1);
        }

        if(!blog.getDislikeIDs().contains(loggedInUser)) {
            blog.getDislikeIDs().add(loggedInUser);
            blog.setDislikeCount(blog.getDislikeCount() + 1);
        }
        blogRepository.save(blog);
        return blog;
    }

    public Blog removeLike(String blogID) {
        String loggedInUser = currentID();
        Blog blog = blogRepository.findByID(blogID).orElseThrow();

        if(blog.getLikeIDs().contains(loggedInUser)) {
            blog.getLikeIDs().remove(loggedInUser);
            blog.setLikeCount(blog.getLikeCount() - 1);
        }
        blogRepository.save(blog);
        return blog;
    }

    public Blog removeDislike(String blogID) {
        String loggedInUser = currentID();
        Blog blog = blogRepository.findByID(blogID).orElseThrow();

        if(blog.getDislikeIDs().contains(loggedInUser)) {
            blog.getDislikeIDs().remove(loggedInUser);
            blog.setDislikeCount(blog.getDislikeCount() - 1);
        }

        blogRepository.save(blog);
        return blog;
    }

    public List<String> likedUsers(String blogID) {
        Blog blog = blogRepository.findByID(blogID).orElseThrow();
        return blog.getLikeIDs();
    }

    public int likedCount(String blogID) {
        Blog blog = blogRepository.findByID(blogID).orElseThrow();
        return blog.getLikeCount();
    }

    public int dislikedCount(String blogID) {
        Blog blog = blogRepository.findByID(blogID).orElseThrow();
        return blog.getDislikeCount();
    }

    public void save(Blog blog) {
        blogRepository.save(blog);
    }

}
