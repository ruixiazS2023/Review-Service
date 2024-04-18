package com.sda.project.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 *
 * @TableName review
 */
@TableName(value ="review")
@Data
public class Review implements Serializable, ICommentOriginator{
    /**
     *
     */
    @TableId
    private String rid;

    /**
     *
     */
    private String content;

    /**
     *
     */
    private Date date;

    /**
     *
     */
    private String uid;

    /**
     *
     */
    @JsonIgnore
    private String parentRid;

    /**
     *
     */
    @JsonIgnore
    private String topicId;

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
                && (this.getTopicId() == null ? other.getTopicId() == null : this.getTopicId().equals(other.getTopicId()));
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
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public CommentMemento createMemento() {
        CommentMemento memento = new CommentMemento();
        String cmid = UUID.randomUUID().toString().substring(0,8);
        //TODO whether set userName or not
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