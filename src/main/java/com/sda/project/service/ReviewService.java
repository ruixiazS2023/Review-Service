package com.sda.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sda.project.domain.CommentMemento;
import com.sda.project.domain.PageResult;
import com.sda.project.domain.Review;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * This interface is used to defines what service should provided for the review.
 *
 */

public interface ReviewService extends IService<Review> {
    /**
     * This method is used to add a review.
     *
     * @param topicId the topic id of the review
     * @param uid the user id of the review
     * @param parentId the parent id of the review
     * @param content the content of the review
     * @param date the date of the review
     * @return the review id
     */
    String addReview(String topicId,String uid, String parentId, String content, Timestamp date);

    /**
     * This method is used to delete a review.
     *
     * @param rid the review id
     */
    void deleteReview(String rid);

    /**
     * This method is used to edit a review.
     *
     * @param rid the review id
     * @param newCentent the new content of the review
     * @param date the date of the review
     */
    void editReview(String rid, String newCentent,Timestamp date);
    /**
     * This method is used to undo the edit of a review.
     *
     * @param rid the review id
     * @param historyId the history id of the review
     * @param updatedTime the updated time of the review
     */
    void undoEdit(String rid, String historyId,Timestamp updatedTime);

    /**
     * This method is used to get the review by the review id.
     *
     * @param rid the review id
     * @return the review
     */
    Review getReviewByRid(String rid);

    /**
     * This method is used to delete the comment history by the history id.
     *
     * @param historyId the history id
     * @return
     */
    void deleteCommentHistoryById(String historyId);

    /**
     * This method is used to get the review page by the topic id.
     *
     * @param topicId the topic id
     * @param page the page number
     * @return the page result
     */
    PageResult<?> getReviewPageByTopicId(String topicId, int page);

    /**
     * This method is used to get the review memento page by the review id.
     *
     * @param rid the review id
     * @param page the page number
     * @return the page result
     */
    PageResult<?> getReviewMementoPageByrId(String rid, int page);

    /**
     * This method is used to get the replies page by the parent id.
     *
     * @param rid the parent rid
     * @param page the page number
     * @return the page result
     */
    PageResult<?> getRepliesPageByrid(String rid, int page);

    /**
     * This method is used to get the user review page by the user id.
     *
     * @param uid the user id
     * @param page the page number
     * @return the page result
     */
    PageResult<?> getUserReviewPageByUid(String uid, int page);



}
