package cn.com.seu.tonguetip.admin.entity;

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
 * @since 2020-07-22
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

    public String getPictruePath() {
        return PictruePath;
    }

    public LocalDateTime getTime() {
        return Time;
    }

    public Integer getCheckID() {
        return CheckID;
    }

    public Integer getUserID() {
        return UserID;
    }
}
