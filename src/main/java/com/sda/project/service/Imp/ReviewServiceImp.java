package com.sda.project.service.Imp;
import java.util.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sda.project.domain.CommentMemento;
import com.sda.project.domain.PageResult;
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

    final int PAGESIZE = 10;


    @Override
    public String addReview(String topicId,String uid, String parentId, String content, Timestamp date) {
        String rid = UUID.randomUUID().toString().substring(0,8);
        Review review = new Review();
        review.setRid(rid);
        review.setTopicId(topicId);
        review.setUid(uid);
        review.setParentRid(parentId);
        review.setContent(content);
        review.setDate(date);
        this.save(review);
        return rid;
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
        history.setDate(date);
        commentMementoMapper.insert(history);
        review.setContent(newCentent);
        review.setUpdatedtime(date);
        this.updateById(review);
    }

    @Override
    public void undoEdit(String rid, String historyId,Timestamp updatedTime) {
        Review review = this.getById(rid);
        CommentMemento memento = review.createMemento();
        memento.setDate(updatedTime);
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
    public PageResult<?> getReviewPageByTopicId(String topicId, int page){
        QueryWrapper<Review> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("topic_id", topicId)
                .isNull("parent_rid")
                .orderByDesc("date");
        Page<Review> reviewPage = this.page(new Page<>(page, PAGESIZE), queryWrapper);
        long total = reviewPage.getTotal();
        System.out.println("current page:" + reviewPage.getCurrent());
        System.out.println("The total page:" + reviewPage.getPages());
        boolean hasMore = reviewPage.getCurrent() < reviewPage.getPages();
        return new PageResult<>(reviewPage.getRecords(), hasMore,total);
    }

    public PageResult<?> getReviewMementoPageByrId(String rid, int page){
        QueryWrapper<CommentMemento> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("commentId", rid).orderByDesc("date");
        Page<CommentMemento> commentMementoPage = commentMementoMapper.selectPage(new Page<>(page, PAGESIZE), queryWrapper);
        boolean hasMore = commentMementoPage.getCurrent() < commentMementoPage.getPages();
        long total = commentMementoPage.getTotal();
        return new PageResult<>(commentMementoPage.getRecords(), hasMore,total);
    }
    @Override
    public PageResult<?> getRepliesPageByrid(String rid, int page){
        QueryWrapper<Review> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_rid", rid).orderByDesc("date");
        Page<Review> reviewPage = this.page(new Page<>(page, PAGESIZE), queryWrapper);
        boolean hasMore = reviewPage.getCurrent() < reviewPage.getPages();
        long total = reviewPage.getTotal();
        return new PageResult<>(reviewPage.getRecords(), hasMore, total);
    }

    @Override
    public PageResult<?> getUserReviewPageByUid(String uid, int page){
        QueryWrapper<Review> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid).orderByDesc("date");
        Page<Review> reviewPage = this.page(new Page<>(page, PAGESIZE), queryWrapper);
        boolean hasMore = reviewPage.getCurrent() < reviewPage.getPages();
        long total = reviewPage.getTotal();
        return new PageResult<>(reviewPage.getRecords(), hasMore, total);
    }

    @Override
    public void deleteCommentHistoryById(String historyId) {
        commentMementoMapper.deleteById(historyId);
    }

}
