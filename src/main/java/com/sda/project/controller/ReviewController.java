package com.sda.project.controller;

import com.sda.project.common.Code;
import com.sda.project.common.Result;
import com.sda.project.domain.CommentMemento;
import com.sda.project.domain.Review;
import com.sda.project.request.PostReviewRequest;
import com.sda.project.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

        reviewService.addReview(topicId, postReviewRequest.getUid(), postReviewRequest.getParentId(), postReviewRequest.getContent(), postReviewRequest.getDate());
        return new Result(Code.CREATED, null, "success posting");
    }

    @DeleteMapping("/{rid}")
    Result deleteReview(@PathVariable String rid) {
        reviewService.deleteReview(rid);
        return new Result(Code.NO_CONTENT, null, "success delete");
    }

    @GetMapping("/{topicId}/{rid}")
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
    Result undoEdit(@PathVariable String rid, @PathVariable String historyId) {
        reviewService.undoEdit(rid, historyId);
        return new Result(Code.SUCCESS, null, "success undo");
    }
    
    @GetMapping("/uid/{uid}")
    Result getReviewsByUid(@PathVariable String uid) {
        List<Review> reviewList = reviewService.getReviewByUid(uid);
        return new Result(Code.SUCCESS, reviewList, "success");
    }

}
