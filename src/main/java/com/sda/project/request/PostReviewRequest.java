package com.sda.project.request;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PostReviewRequest {
    String uid;
    String parentId;
    String content;
    Timestamp date;
}
