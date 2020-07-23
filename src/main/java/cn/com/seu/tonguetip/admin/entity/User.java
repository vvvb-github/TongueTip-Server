package cn.com.seu.tonguetip.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("User")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "UserID", type = IdType.AUTO)
    private Integer UserID;

    @TableField("UserName")
    private String UserName;

    @TableField("PhoneNumber")
    private String PhoneNumber;

    @TableField("Password")
    private String Password;

    @TableField("Type")
    private Integer Type;

    @TableField("Priority")
    private Integer Priority;

    @TableField("IconPath")
    private String IconPath;

    public Integer getUserID() {
        return UserID;
    }

    public Integer getPriority() {
        return Priority;
    }

    public String getIconPath() {
        return IconPath;
    }

    public Integer getType() {
        return Type;
    }

    public String getPassword() {
        return Password;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getUserName() {
        return UserName;
    }

    public void setType(Integer type) {
        Type = type;
    }
}
