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
@TableName("UserCheck")
public class UserCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "CheckID", type = IdType.AUTO)
    private Integer CheckID;

    @TableField("UserID")
    private Integer UserID;

    @TableField("Time")
    private LocalDateTime Time;

    @TableField("PictruePath")
    private String PictruePath;

    public Integer getUserID() {
        return UserID;
    }

    public LocalDateTime getTime() {
        return Time;
    }

    public Integer getCheckID() {
        return CheckID;
    }

    public String getPictruePath() {
        return PictruePath;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public void setTime(LocalDateTime time) {
        Time = time;
    }

    public void setCheckID(Integer checkID) {
        CheckID = checkID;
    }

    public void setPictruePath(String pictruePath) {
        PictruePath = pictruePath;
    }
}
