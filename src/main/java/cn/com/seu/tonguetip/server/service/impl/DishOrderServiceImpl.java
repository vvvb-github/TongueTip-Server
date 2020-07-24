package cn.com.seu.tonguetip.server.service.impl;

import cn.com.seu.tonguetip.server.entity.DishOrder;
import cn.com.seu.tonguetip.server.mapper.DishOrderMapper;
import cn.com.seu.tonguetip.server.service.IDishOrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author five_dumplings
 * @since 2020-07-24
 */
@Service
public class DishOrderServiceImpl extends ServiceImpl<DishOrderMapper, DishOrder> implements IDishOrderService {
    @Override
    public DishOrder newDishOrder(Integer userID, Integer dishID, Integer number, Double prices, String orderID, String ps) {
        DishOrder dishOrder= new DishOrder();
        dishOrder.setOrderID(orderID);
        dishOrder.setDishID(dishID);
        dishOrder.setUserID(userID);
        dishOrder.setNumber(number);
        dishOrder.setOrderID(orderID);
        dishOrder.setPrices(prices);
        dishOrder.setPs(ps);
        dishOrder.setTime(LocalDateTime.now());
        dishOrder.setState(0);
        //save(dishOrder);
        return dishOrder;
    }

    @Override
    public List<DishOrder> getUserOrder(Integer userID) {
        QueryWrapper<DishOrder> wrapper=new QueryWrapper<>();
        wrapper.eq("UserID",userID);
        List<DishOrder> dishOrders=list(wrapper);
        return dishOrders;
    }

    @Override
    public List<DishOrder> getHostOrder(Integer hostID) {
        QueryWrapper<DishOrder> wrapper=new QueryWrapper<>();
        wrapper.eq("HostID",hostID);
        wrapper.eq("State",0);
        List<DishOrder> dishOrders=list(wrapper);
        QueryWrapper<DishOrder> wrapper2=new QueryWrapper<>();
        wrapper2.eq("HostID",hostID);
        wrapper2.eq("State",1);
        List<DishOrder> dishOrders2=list(wrapper2);
        dishOrders.addAll(dishOrders2);
        return dishOrders;
    }

    @Override
    public boolean changestate(String orderID,Integer newState) {
        DishOrder dishOrder;
        QueryWrapper<DishOrder> wrapper=new QueryWrapper<>();
        wrapper.eq("OrderID",orderID);
        dishOrder = getOne(wrapper);
        dishOrder.setState(newState);
        return update(dishOrder,wrapper);
    }
}
