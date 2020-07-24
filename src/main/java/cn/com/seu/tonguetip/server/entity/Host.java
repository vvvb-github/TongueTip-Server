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
@TableName("Host")
public class Host implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "HostID", type = IdType.AUTO)
    private Integer HostID;

    @TableField("HostName")
    private String HostName;

    @TableField("PhoneNumber")
    private String PhoneNumber;

    @TableField("Location")
    private String Location;

    @TableField("Star")
    private Double Star;

    @TableField("PicturePath")
    private String PicturePath;

    @TableField("UserID")
    private Integer UserID;

    @TableField("introduction")
    private String introduction;

    public Integer getUserID() {
        return UserID;
    }

    public Integer getHostID() {
        return HostID;
    }

    public String getPicturePath() {
        return PicturePath;
    }

    public String getIntroduction() {
        return introduction;
    }

    public Double getStar() {
        return Star;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getHostName() {
        return HostName;
    }

    public String getLocation() {
        return Location;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public void setHostID(Integer hostID) {
        HostID = hostID;
    }

    public void setPicturePath(String picturePath) {
        PicturePath = picturePath;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setStar(Double star) {
        Star = star;
    }

    public void setHostName(String hostName) {
        HostName = hostName;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
}
