package com.example.blogpost.berkayerol.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
public class Blog {
    @Id
    private String ID;

    private LocalDateTime time;

    private String context;

    private String title;

    private String userID;

    private List<String> commentIDs;

    private List<String> likeIDs;

    private List<String> dislikeIDs;

    private int likeCount;

    private int dislikeCount;
}
