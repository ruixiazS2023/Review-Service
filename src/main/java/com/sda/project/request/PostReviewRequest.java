package com.sda.project.request;

import lombok.Data;

import java.sql.Timestamp;


/**
 *
 * This class is used to store the request of post review.
 *
 */
@Data
public class PostReviewRequest {
    String uid;
    String parentId;
    String content;
    Timestamp date;
}
