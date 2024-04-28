package com.sda.project.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * This class is used to store the CommentMemento information.
 */
@TableName(value ="CommentMemento")
@Data
public class CommentMemento implements Serializable {
    /**
     *  the id of the CommentMemento
     */
    @TableId
    private String cmid;

    /**
     * the user id of the CommentMemento
     */
    private String uid;

    /**
     * the post or last edit date of the Comment
     */
    private Date date;

    /**
     * the content of the Comment
     */
    private String content;

    /**
     * the Review id of related Review
     */
    private String commentid;

    /**
     * the isdelete of the CommentMemento for logical delete
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
        CommentMemento other = (CommentMemento) that;
        return (this.getCmid() == null ? other.getCmid() == null : this.getCmid().equals(other.getCmid()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getCommentid() == null ? other.getCommentid() == null : this.getCommentid().equals(other.getCommentid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCmid() == null) ? 0 : getCmid().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getCommentid() == null) ? 0 : getCommentid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cmid=").append(cmid);
        sb.append(", username=").append(uid);
        sb.append(", date=").append(date);
        sb.append(", content=").append(content);
        sb.append(", commentid=").append(commentid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}