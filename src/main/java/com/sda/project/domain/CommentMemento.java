package com.sda.project.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @TableName CommentMemento
 */
@TableName(value ="CommentMemento")
@Data
public class CommentMemento implements Serializable {
    /**
     * 
     */
    @TableId
    private String cmid;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private Date date;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private String commentid;

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
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getCommentid() == null ? other.getCommentid() == null : this.getCommentid().equals(other.getCommentid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCmid() == null) ? 0 : getCmid().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
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
        sb.append(", username=").append(username);
        sb.append(", date=").append(date);
        sb.append(", content=").append(content);
        sb.append(", commentid=").append(commentid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}