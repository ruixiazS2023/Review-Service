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

@RestController
@CrossOrigin
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping("/{topicId}")
    ResponseEntity<?> getReviewsByTopicId(@PathVariable String topicId, @RequestParam(defaultValue = "0") int page) {
        if (page < 0 || topicId == null || topicId.isEmpty()) {
            throw new IllegalArgumentException("invalid request");
        }
        System.out.println("page: " + page);
        PageResult<?> reviewList = reviewService.getReviewPageByTopicId(topicId, page);
        return ResponseUtils.success(reviewList);
    }

    @PostMapping("/{topicId}")
    ResponseEntity<?> addReview(@PathVariable String topicId, @RequestBody PostReviewRequest postReviewRequest) {
        if (postReviewRequest == null || postReviewRequest.getUid() == null || postReviewRequest.getUid().isEmpty() || postReviewRequest.getContent() == null || postReviewRequest.getContent().isEmpty() || postReviewRequest.getDate() == null) {
            throw new IllegalArgumentException("invalid request");
        }
        String rid = reviewService.addReview(topicId, postReviewRequest.getUid(), postReviewRequest.getParentId(), postReviewRequest.getContent(), postReviewRequest.getDate());
        return ResponseUtils.success(rid);

    }

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

    @DeleteMapping("/{topicId}/{rid}")
    ResponseEntity<?> deleteReview(@PathVariable String rid) {
        if (rid == null || rid.isEmpty()) {
            throw new IllegalArgumentException("invalid request");
        }
        reviewService.deleteReview(rid);
        return ResponseUtils.success(null);
    }

    @PutMapping("/{topicId}/{rid}")
    ResponseEntity<?> editReview(@PathVariable String rid, @RequestBody PostReviewRequest postReviewRequest) {
        if (postReviewRequest == null || postReviewRequest.getContent() == null || postReviewRequest.getContent().isEmpty() || postReviewRequest.getDate() == null) {
            throw new IllegalArgumentException("invalid request");
        }
        reviewService.editReview(rid, postReviewRequest.getContent(), postReviewRequest.getDate());
        return ResponseUtils.success(null);
    }

    @GetMapping("replies/{rid}")
    ResponseEntity<?> getReplies(@PathVariable String rid, @RequestParam(defaultValue = "0") int page) {
        if (page < 0 || rid == null || rid.isEmpty()) {
            throw new IllegalArgumentException("invalid request");
        }
        PageResult<?> replyList = reviewService.getRepliesPageByrid(rid, page);
        return ResponseUtils.success(replyList);
    }

    @GetMapping("/histories/{rid}")
    ResponseEntity<?> getCommentHistory(@PathVariable String rid, @RequestParam(defaultValue = "0") int page) {
        if (page < 0 || rid == null || rid.isEmpty()) {
            throw new IllegalArgumentException("invalid request");
        }
        PageResult<?> commentMementoList = reviewService.getReviewMementoPageByrId(rid, page);
        return ResponseUtils.success(commentMementoList);
    }

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


    @DeleteMapping("/histories/undo/{historyId}")
    ResponseEntity<?> deleteCommentHistory(@PathVariable String historyId) {
        if (historyId == null || historyId.isEmpty()) {
            throw new IllegalArgumentException("invalid request");
        }
        reviewService.deleteCommentHistoryById(historyId);
        return ResponseUtils.success(null);
    }

    @GetMapping("/uids/{uid}")
    ResponseEntity<?> getUserReviewPageByUid(@PathVariable String uid, @RequestParam(defaultValue = "0") int page) {
        if (page < 0 || uid == null || uid.isEmpty()) {
            throw new IllegalArgumentException("invalid request");
        }
        PageResult<?> reviewList = reviewService.getUserReviewPageByUid(uid, page);
        return ResponseUtils.success(reviewList);
    }
}
