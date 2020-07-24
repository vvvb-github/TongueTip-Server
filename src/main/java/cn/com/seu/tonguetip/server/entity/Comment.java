package cn.com.seu.tonguetip.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author five_dumplings
 * @since 2020-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("Comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "CommentID", type = IdType.AUTO)
    private Integer CommentID;

    @TableField("UserID")
    private Integer UserID;

    @TableField("DishID")
    private Integer DishID;

    @TableField("Content")
    private String Content;

    @TableField("Star")
    private Double Star;

    @TableField("Time")
    private LocalDateTime Time;

    public Integer getUserID() {
        return UserID;
    }

    public LocalDateTime getTime() {
        return Time;
    }

    public Double getStar() {
        return Star;
    }

    public Integer getCommentID() {
        return CommentID;
    }

    public Integer getDishID() {
        return DishID;
    }

    public String getContent() {
        return Content;
    }

    public void setCommentID(Integer commentID) {
        CommentID = commentID;
    }

    public void setContent(String content) {
        Content = content;
    }

    public void setDishID(Integer dishID) {
        DishID = dishID;
    }

    public void setStar(Double star) {
        Star = star;
    }

    public void setTime(LocalDateTime time) {
        Time = time;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }
}
