package com.sda.project.controller;

import com.sda.project.common.Code;
import com.sda.project.common.ResponseEntity;
import com.sda.project.common.ResponseUtils;
import com.sda.project.domain.PageResult;
import com.sda.project.domain.Review;
import com.sda.project.request.PostReviewRequest;
import com.sda.project.request.UndoRequest;
import com.sda.project.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;


/**
 *
 * This class is used to handle the requests of reviews.
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    /**
     * This method is used to handle the request which wants to get reviews list by topic id.
     *
     * @param topicId
     * @param page
     * @return
     */
    @GetMapping("/{topicId}")
    ResponseEntity<?> getReviewsByTopicId(@PathVariable String topicId, @RequestParam(defaultValue = "0") int page) {
        if (page < 0 || topicId == null || topicId.isEmpty()) {
            throw new IllegalArgumentException("invalid request");
        }
        System.out.println("page: " + page);
        PageResult<?> reviewList = reviewService.getReviewPageByTopicId(topicId, page);
        return ResponseUtils.success(reviewList);
    }

    /**
     * This method is used to handle the request which wants to add a review.
     *
     * @param topicId
     * @param postReviewRequest
     * @return
     */
    @PostMapping("/{topicId}")
    ResponseEntity<?> addReview(@PathVariable String topicId, @RequestBody PostReviewRequest postReviewRequest) {
        if (postReviewRequest == null || postReviewRequest.getUid() == null || postReviewRequest.getUid().isEmpty() || postReviewRequest.getContent() == null || postReviewRequest.getContent().isEmpty() || postReviewRequest.getDate() == null) {
            throw new IllegalArgumentException("invalid request");
        }
        String rid = reviewService.addReview(topicId, postReviewRequest.getUid(), postReviewRequest.getParentId(), postReviewRequest.getContent(), postReviewRequest.getDate());
        return ResponseUtils.success(rid);

    }

    /**
     * This method is used to handle the request which wants to get a review by rid.
     *
     * @param rid
     * @return
     */
    @GetMapping("/{topicId}/{rid}")
    ResponseEntity<?> getReviewByRid(@PathVariable String rid) {
        if (rid == null || rid.isEmpty()) {
            throw new IllegalArgumentException("invalid request");
        }
        Review review = reviewService.getReviewByRid(rid);
        if (review != null) {
            return ResponseUtils.success(review);
        }
        return ResponseUtils.error(Code.NOT_FOUND, "review not found");
    }

    /**
     * This method is used to handle the request which wants to delete a review by rid.
     *
     * @param rid
     * @return
     */
    @DeleteMapping("/{topicId}/{rid}")
    ResponseEntity<?> deleteReview(@PathVariable String rid) {
        if (rid == null || rid.isEmpty()) {
            throw new IllegalArgumentException("invalid request");
        }
        reviewService.deleteReview(rid);
        return ResponseUtils.success(null);
    }

    /**
     * This method is used to handle the request which wants to edit a review by rid.
     *
     * @param rid
     * @param postReviewRequest
     * @return
     */
    @PutMapping("/{topicId}/{rid}")
    ResponseEntity<?> editReview(@PathVariable String rid, @RequestBody PostReviewRequest postReviewRequest) {
        if (postReviewRequest == null || postReviewRequest.getContent() == null || postReviewRequest.getContent().isEmpty() || postReviewRequest.getDate() == null) {
            throw new IllegalArgumentException("invalid request");
        }
        reviewService.editReview(rid, postReviewRequest.getContent(), postReviewRequest.getDate());
        return ResponseUtils.success(null);
    }

    /**
     * This method is used to handle the request which wants to get Replies list by related rid.
     *
     * @param rid
     * @param page
     * @return
     */
    @GetMapping("replies/{rid}")
    ResponseEntity<?> getReplies(@PathVariable String rid, @RequestParam(defaultValue = "0") int page) {
        if (page < 0 || rid == null || rid.isEmpty()) {
            throw new IllegalArgumentException("invalid request");
        }
        PageResult<?> replyList = reviewService.getRepliesPageByrid(rid, page);
        return ResponseUtils.success(replyList);
    }

    /**
     * This method is used to handle the request which wants to get CommentHistory list by related rid.
     *
     * @param rid
     * @param page
     * @return
     */
    @GetMapping("/histories/{rid}")
    ResponseEntity<?> getCommentHistory(@PathVariable String rid, @RequestParam(defaultValue = "0") int page) {
        if (page < 0 || rid == null || rid.isEmpty()) {
            throw new IllegalArgumentException("invalid request");
        }
        PageResult<?> commentMementoList = reviewService.getReviewMementoPageByrId(rid, page);
        return ResponseUtils.success(commentMementoList);
    }

    /**
     * This method is used to handle the request which wants to undo edit by rid and historyId.
     *
     * @param undoRequest
     * @return
     */
    @PutMapping("/histories/undo")
    ResponseEntity<?> undoEdit(@RequestBody UndoRequest undoRequest) {
        if(undoRequest == null) {
            throw new IllegalArgumentException("invalid request");
        }
        String rid = undoRequest.getRid();
        String historyId = undoRequest.getHistoryId();
        if (undoRequest.getUpdateTime() == null
                || historyId == null
                || historyId.isEmpty()
                || rid == null
                || rid.isEmpty()) {
            throw new IllegalArgumentException("invalid request");
        }
        Timestamp updateTime = undoRequest.getUpdateTime();
        reviewService.undoEdit(rid, historyId, updateTime);
        return ResponseUtils.success(null);
    }

    /**
     * This method is used to handle the request which wants to delete a CommentHistory by historyId.
     *
     * @param historyId
     * @return
     */
    @DeleteMapping("/histories/undo/{historyId}")
    ResponseEntity<?> deleteCommentHistory(@PathVariable String historyId) {
        if (historyId == null || historyId.isEmpty()) {
            throw new IllegalArgumentException("invalid request");
        }
        reviewService.deleteCommentHistoryById(historyId);
        return ResponseUtils.success(null);
    }

    /**
     * This method is used to handle the request which wants to get a review list by uid.
     *
     * @param uid
     * @param page
     * @return
     */
    @GetMapping("/uids/{uid}")
    ResponseEntity<?> getUserReviewPageByUid(@PathVariable String uid, @RequestParam(defaultValue = "0") int page) {
        if (page < 0 || uid == null || uid.isEmpty()) {
            throw new IllegalArgumentException("invalid request");
        }
        PageResult<?> reviewList = reviewService.getUserReviewPageByUid(uid, page);
        return ResponseUtils.success(reviewList);
    }
}
