package cn.com.seu.tonguetip.server.service;

import cn.com.seu.tonguetip.server.entity.DishOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author five_dumplings
 * @since 2020-07-24
 */
public interface IDishOrderService extends IService<DishOrder> {
    DishOrder newDishOrder(Integer userID, Integer dishID, Integer number, Double prices, String orderID, String ps);
    List<DishOrder> getUserOrder(Integer userID);
    List<DishOrder> getHostOrder(Integer hostID);
    boolean changestate(String orderID,Integer newState);
    void deleteOrder(Integer userID);
    List<Integer> getRecommendHostID(Integer userID);
    Integer getUserID(String orderID);
    Integer gethostID(String orderID);
}
