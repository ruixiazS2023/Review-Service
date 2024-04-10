package com.sda.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sda.project.domain.CommentMemento;
import com.sda.project.domain.Review;

import java.sql.Timestamp;
import java.util.List;

public interface ReviewService extends IService<Review> {
    void addReview(String topicId,String uid, String parentId, String content, Timestamp date);
    void deleteReview(String rid);
    List<Review> getReviewByTopicId(String topicId);
    List<Review> getReviewByUid(String uid);
    List<Review> getReplies(String rid);

    void editReview(String rid, String newCentent,Timestamp date);
    void undoEdit(String rid, String historyId);
    List<CommentMemento> getCommentHistory(String rid);



}
