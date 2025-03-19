package com.example.blogpost.berkayerol.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditRequest {
    private LocalDateTime time;

    private String context;

    private String title;

    private String blogID;
}
