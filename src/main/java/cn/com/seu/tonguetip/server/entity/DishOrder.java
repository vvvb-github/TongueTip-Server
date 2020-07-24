package cn.com.seu.tonguetip.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;

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
@TableName("DishOrder")
public class DishOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("OrderID")
    private String OrderID;

    @TableField("DishID")
    private Integer DishID;

    @TableField("HostID")
    private Integer HostID;

    @TableField("UserID")
    private Integer UserID;

    @TableField("Time")
    private LocalDateTime Time;

    @TableField("State")
    private Integer State;

    @TableField("Number")
    private Integer Number;

    @TableField("PS")
    private String ps;

    @TableField("Prices")
    private Double Prices;

    public Integer getHostID() {
        return HostID;
    }

    public Integer getDishID() {
        return DishID;
    }

    public Integer getUserID() {
        return UserID;
    }

    public Double getPrices() {
        return Prices;
    }

    public Integer getNumber() {
        return Number;
    }

    public Integer getState() {
        return State;
    }

    public String getOrderID() {
        return OrderID;
    }

    public String getPs() {
        return ps;
    }

    public void setHostID(Integer hostID) {
        HostID = hostID;
    }

    public void setDishID(Integer dishID) {
        DishID = dishID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public void setTime(LocalDateTime time) {
        Time = time;
    }

    public void setNumber(Integer number) {
        Number = number;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public void setPrices(Double prices) {
        Prices = prices;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public void setState(Integer state) {
        State = state;
    }

    public String getTime() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //LocalDateTime time = LocalDateTime.now();
        String localTime = df.format(Time);
        return localTime;
    }
}
