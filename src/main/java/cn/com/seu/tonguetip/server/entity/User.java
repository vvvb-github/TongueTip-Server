package cn.com.seu.tonguetip.server.entity;

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
 * @since 2020-07-24
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

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public Integer getUserID() {
        return UserID;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public Integer getType() {
        return Type;
    }

    public String getIconPath() {
        return IconPath;
    }

    public Integer getPriority() {
        return Priority;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public void setType(Integer type) {
        Type = type;
    }

    public void setIconPath(String iconPath) {
        IconPath = iconPath;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setPriority(Integer priority) {
        Priority = priority;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
