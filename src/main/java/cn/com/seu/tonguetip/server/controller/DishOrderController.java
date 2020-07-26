package cn.com.seu.tonguetip.server.controller;


import cn.com.seu.tonguetip.server.entity.Dish;
import cn.com.seu.tonguetip.server.entity.DishOrder;
import cn.com.seu.tonguetip.server.service.IDishOrderService;
import cn.com.seu.tonguetip.server.service.IDishService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.ObjectUtils;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author five_dumplings
 * @since 2020-07-23
 */
@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*",allowCredentials = "true")
public class DishOrderController {

    @Autowired
    private IDishOrderService dishOrderService;

    @Autowired
    private IDishService dishService;

    @RequestMapping(value="/add",method = RequestMethod.POST)
    public JSONObject addOrder(Integer userID, Integer dishID, Integer number, Double prices, String orderID, String ps)
    {

        DishOrder dishorder=dishOrderService.newDishOrder(userID, dishID, number, prices, orderID, ps);
        JSONObject jsonObj= new JSONObject();
        Dish dish =dishService.searchHostID(dishID);
        dishorder.setHostID(dish.getHostID());

        dishOrderService.save(dishorder);

        String dishName=dish.getDishName();

        String date=dishorder.getTime();
        String image=dish.getPicturePath();
        JSONObject dishOrder= new JSONObject();
        dishOrder.put("date",date);
        dishOrder.put("dishName",dishName);
        dishOrder.put("number",number);
        dishOrder.put("prices",prices);
        dishOrder.put("dishID",dishID);
        dishOrder.put("orderID",orderID);
        dishOrder.put("image",image);
        dishOrder.put("PS",ps);

        jsonObj.put("status",1);
        jsonObj.put("dishOrder",dishOrder);

        return jsonObj;
    };

    @RequestMapping(value="/userOrder",method = RequestMethod.GET)
    public JSONObject userOrder(Integer userID) {

        JSONObject jsonObj = new JSONObject();
        try {
            List<DishOrder> orderList = dishOrderService.getUserOrder(userID);
            List<JSONObject> tempList = new ArrayList<>();
            if(orderList!= null)
            {
                for (DishOrder order : orderList) {

                    JSONObject temp = new JSONObject();

                    temp.put("date", order.getTime());

                    Dish dish = dishService.searchHostID(order.getDishID());
                    temp.put("dishName", dish.getDishName());

                    temp.put("number", order.getNumber());
                    temp.put("prices", order.getPrices());
                    temp.put("dishID", order.getDishID());
                    temp.put("orderID", order.getOrderID());
                    temp.put("image", dish.getPicturePath());
                    temp.put("PS", order.getPs());
                    tempList.add(temp);
                }
                jsonObj.put("status", 1);
                jsonObj.put("cusOrder", tempList);
            }
            else{
                jsonObj.put("status", 0);
                jsonObj.put("errmsg", "无详情");
            }
        } catch (Exception ex) {
            jsonObj.put("status", 0);
            jsonObj.put("errmsg", "无对应详情");
        }
        return jsonObj;
    }
    @RequestMapping(value="/hostOrder",method = RequestMethod.GET)
    public JSONObject hostOrder(Integer hostID) {

        JSONObject jsonObj = new JSONObject();
        try {
            List<DishOrder> orderList = dishOrderService.getHostOrder(hostID);
            List<JSONObject> tempList = new ArrayList<>();
            if(orderList!= null)
            {
                for (DishOrder order : orderList) {

                    JSONObject temp = new JSONObject();
                    Dish dish =dishService.searchHostID(order.getDishID());

                    temp.put("picPath", dish.getPicturePath());

                    temp.put("dishName", dish.getDishName());
                    temp.put("prices", order.getPrices());
                    temp.put("orderID", order.getOrderID());
                    temp.put("date",order.getTime());

                    temp.put("orderStatus",order.getState());
                    temp.put("PS", order.getPs());
                    temp.put("number", order.getNumber());
                    tempList.add(temp);
                }
                jsonObj.put("status", 1);
                jsonObj.put("hostOrders", tempList);
            }
            else{
                jsonObj.put("status", 0);
                jsonObj.put("errmsg", "无详情");
            }
        } catch (Exception ex) {
            jsonObj.put("status", 0);
            jsonObj.put("errmsg", "无对应详情");
        }
        return jsonObj;
    }

    @RequestMapping(value="/change",method = RequestMethod.GET)
    public JSONObject changeState(String orderID,Integer newState) {

        JSONObject jsonObj = new JSONObject();
        try {
            dishOrderService.changestate(orderID,newState);
            jsonObj.put("status", 1);
        }
        catch (Exception ex) {
            jsonObj.put("status", 0);
            jsonObj.put("errmsg", "无对应详情");
        }
        return jsonObj;
    }
}
