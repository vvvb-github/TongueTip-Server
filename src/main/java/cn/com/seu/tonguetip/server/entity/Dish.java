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
@TableName("Dish")
public class Dish implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "DishID", type = IdType.AUTO)
    private Integer DishID;

    @TableField("DishName")
    private String DishName;

    @TableField("Price")
    private Double Price;

    @TableField("Star")
    private Double Star;

    @TableField("HostID")
    private Integer HostID;

    @TableField("Introduction")
    private String Introduction;

    @TableField("Tag")
    private String Tag;

    @TableField("Sales")
    private Integer Sales;

    @TableField("PicturePath")
    private String PicturePath;

    public Integer getDishID() {
        return DishID;
    }

    public Double getStar() {
        return Star;
    }

    public Double getPrice() {
        return Price;
    }

    public Integer getHostID() {
        return HostID;
    }

    public Integer getSales() {
        return Sales;
    }

    public String getDishName() {
        return DishName;
    }

    public String getIntroduction() {
        return Introduction;
    }

    public String getPicturePath() {
        return PicturePath;
    }

    public String getTag() {
        return Tag;
    }

    public void setStar(Double star) {
        Star = star;
    }

    public void setDishID(Integer dishID) {
        DishID = dishID;
    }

    public void setDishName(String dishName) {
        DishName = dishName;
    }

    public void setHostID(Integer hostID) {
        HostID = hostID;
    }

    public void setIntroduction(String introduction) {
        Introduction = introduction;
    }

    public void setPicturePath(String picturePath) {
        PicturePath = picturePath;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public void setSales(Integer sales) {
        Sales = sales;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

}
