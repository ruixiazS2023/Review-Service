package com.sda.project.controller;

import com.sda.project.common.Code;
import com.sda.project.common.Result;
import com.sda.project.domain.CommentMemento;
import com.sda.project.domain.Review;
import com.sda.project.request.PostReviewRequest;
import com.sda.project.request.UndoRequest;
import com.sda.project.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;


    @GetMapping("/{topicId}")
    Result getReviewsByTopicId(@PathVariable String topicId) {
        List<Review> reviewList = reviewService.getReviewByTopicId(topicId);
        return new Result(Code.SUCCESS, reviewList, "success");
    }

    @PostMapping("/{topicId}")
    Result addReview(@PathVariable String topicId, @RequestBody PostReviewRequest postReviewRequest) {
        String rid = reviewService.addReview(topicId, postReviewRequest.getUid(), postReviewRequest.getParentId(), postReviewRequest.getContent(), postReviewRequest.getDate());
        return new Result(Code.CREATED, rid, "success posting");
    }

    @DeleteMapping("/{rid}")
    Result deleteReview(@PathVariable String rid) {
        reviewService.deleteReview(rid);
        return new Result(Code.NO_CONTENT, null, "success delete");
    }

    @GetMapping("/{rid}/replies")
    Result getReplies(@PathVariable String rid) {
        List<Review> replyList = reviewService.getReplies(rid);
        return new Result(Code.SUCCESS, replyList, "success");
    }

    @PutMapping("/{topicId}/{rid}")
    Result editReview(@PathVariable String rid, @RequestBody PostReviewRequest postReviewRequest) {
        reviewService.editReview(rid, postReviewRequest.getContent(), postReviewRequest.getDate());
        return new Result(Code.SUCCESS, null, "success edit");
    }

    @GetMapping("/{rid}/history")
    Result getCommentHistory(@PathVariable String rid) {
        List<CommentMemento> commentMementoList = reviewService.getCommentHistory(rid);
        return new Result(Code.SUCCESS, commentMementoList, "success");
    }

    @PutMapping("/{rid}/undo/{historyId}")
    Result undoEdit(@PathVariable String rid, @PathVariable String historyId, @RequestBody UndoRequest undoRequest) {
        Timestamp updateTime = undoRequest.getUpdateTime();
        reviewService.undoEdit(rid, historyId, updateTime);
        return new Result(Code.SUCCESS, null, "success undo");
    }
    
    @GetMapping("/uid/{uid}")
    Result getReviewsByUid(@PathVariable String uid) {
        List<Review> reviewList = reviewService.getReviewByUid(uid);
        return new Result(Code.SUCCESS, reviewList, "success");
    }

    @GetMapping("/rids/{rid}")
    Result getReviewByRid(@PathVariable String rid) {
        Review review = reviewService.getReviewByRid(rid);
        if (review != null) {
            return new Result(Code.SUCCESS, review, "success");
        }
        return new Result(Code.NO_CONTENT, null, "not found");
    }

    @DeleteMapping("/{rid}/history/{historyId}")
    Result deleteCommentHistory(@PathVariable String historyId) {
        reviewService.deleteCommentHistoryById(historyId);
        return new Result(Code.NO_CONTENT, null, "success delete");
    }

}
