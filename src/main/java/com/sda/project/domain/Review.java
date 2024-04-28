package com.sda.project.domain;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 *
 * This class is used to store the review information
 * and implements ICommentOriginator.
 *
 */
@TableName(value ="review")
@Data
public class Review implements Serializable, ICommentOriginator{
    /**
     * the id of the review
     */
    @TableId
    private String rid;

    /**
     * the content of the review
     */
    private String content;

    /**
     * the post date of the review
     */
    private Date date;

    /**
     * the user id of the review
     */
    private String uid;

    /**
     * the parent review id of the review
     */
    @JsonIgnore
    private String parentRid;

    /**
     * the topic id of the review
     */
    private String topicId;

    /**
     * the updated time of the review
     */
    private Date updatedtime;

    /**
     * the isdelete of the review for logical delete
     * 0 is not delete 1 is delete
     */
    @JsonIgnore
    @TableLogic
    private Integer isdelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Review other = (Review) that;
        return (this.getRid() == null ? other.getRid() == null : this.getRid().equals(other.getRid()))
                && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
                && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
                && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
                && (this.getParentRid() == null ? other.getParentRid() == null : this.getParentRid().equals(other.getParentRid()))
                && (this.getTopicId() == null ? other.getTopicId() == null : this.getTopicId().equals(other.getTopicId()))
                && (this.getUpdatedtime() == null ? other.getUpdatedtime() == null : this.getUpdatedtime().equals(other.getUpdatedtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRid() == null) ? 0 : getRid().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getParentRid() == null) ? 0 : getParentRid().hashCode());
        result = prime * result + ((getTopicId() == null) ? 0 : getTopicId().hashCode());
        result = prime * result + ((getUpdatedtime() == null) ? 0 : getUpdatedtime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rid=").append(rid);
        sb.append(", content=").append(content);
        sb.append(", date=").append(date);
        sb.append(", uid=").append(uid);
        sb.append(", parentRid=").append(parentRid);
        sb.append(", topicId=").append(topicId);
        sb.append(", updatedtime=").append(updatedtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public CommentMemento createMemento() {
        CommentMemento memento = new CommentMemento();
        String cmid = UUID.randomUUID().toString().substring(0,8);
        memento.setCmid(cmid);
        memento.setUid(this.uid);
        memento.setCommentid(this.rid);
        memento.setContent(this.content);
        memento.setDate(this.date);
        return memento;
    }

    @Override
    public void restoreFromMemento(CommentMemento memento) {
        this.content = memento.getContent();
    }
}