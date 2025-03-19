package com.example.blogpost.berkayerol.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document
public class Comment {

    @Id
    private String ID;

    private LocalDateTime time;

    private String context;

    private String UserID;

    private String blogPostID;
}
