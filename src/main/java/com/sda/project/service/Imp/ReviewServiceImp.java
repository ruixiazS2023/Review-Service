package com.sda.project.service.Imp;
import java.util.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sda.project.domain.CommentMemento;
import com.sda.project.domain.Review;
import com.sda.project.mapper.CommentmementoMapper;
import com.sda.project.service.ReviewService;
import com.sda.project.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ReviewServiceImp extends ServiceImpl<ReviewMapper, Review>
        implements ReviewService {

    @Autowired
    private CommentmementoMapper commentMementoMapper;

    @Override
    public void addReview(String topicId,String uid, String parentId, String content, Timestamp date) {
        String rid = UUID.randomUUID().toString().substring(0,8);
        Review review = new Review();
        review.setRid(rid);
        review.setTopicId(topicId);
        review.setUid(uid);
        review.setParentRid(parentId);
        review.setContent(content);
        review.setDate(date);
        this.save(review);
    }

    @Override
    public void deleteReview(String rid) {
        this.removeById(rid);
    }

    @Override
    public List<Review> getReviewByTopicId(String topicId) {
        QueryWrapper<Review> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("topic_id", topicId)
                .isNull("parent_rid")
                .orderByDesc("date");
        return this.list(queryWrapper);
    }

    @Override
    public List<Review> getReviewByUid(String uid) {
        QueryWrapper<Review> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid);
        return this.list(queryWrapper);
    }

    @Override
    public List<Review> getReplies(String rid) {
        QueryWrapper<Review> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_rid", rid).orderByDesc("date");
        return this.list(queryWrapper);
    }

    @Override
    public void editReview(String rid, String newCentent,Timestamp date) {
        Review review = this.getById(rid);
        CommentMemento history = review.createMemento();
        commentMementoMapper.insert(history);
        review.setContent(newCentent);
        review.setUpdatedtime(date);
        this.updateById(review);
    }

    @Override
    public void undoEdit(String rid, String historyId,Timestamp updatedTime) {
        Review review = this.getById(rid);
        CommentMemento memento = review.createMemento();
        commentMementoMapper.insert(memento);
        CommentMemento history = commentMementoMapper.selectById(historyId);
        review.restoreFromMemento(history);
        review.setUpdatedtime(updatedTime);
        this.updateById(review);
        commentMementoMapper.deleteById(historyId);
    }

    @Override
    public List<CommentMemento> getCommentHistory(String rid) {
        QueryWrapper<CommentMemento> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("commentId", rid).orderByDesc("date");
        return commentMementoMapper.selectList(queryWrapper);
    }
    @Override
    public Review getReviewByRid(String rid) {
        return this.getById(rid);
    }

    @Override
    public void deleteCommentHistoryById(String historyId) {
        commentMementoMapper.deleteById(historyId);
    }

}
