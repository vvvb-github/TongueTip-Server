package cn.com.seu.tonguetip.server.controller;


import cn.com.seu.tonguetip.server.entity.Comment;
import cn.com.seu.tonguetip.server.entity.Dish;
import cn.com.seu.tonguetip.server.service.ICommentService;
import cn.com.seu.tonguetip.server.service.IDishOrderService;
import cn.com.seu.tonguetip.server.service.IDishService;
import cn.com.seu.tonguetip.server.entity.User;
import cn.com.seu.tonguetip.server.service.IUserService;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author five_dumplings
 * @since 2020-07-22
 */
@RestController
@RequestMapping("/dish")
@CrossOrigin(origins = "*",allowCredentials = "true")
public class DishController {
    @Autowired
    private IDishService dishService;
    @Autowired
    private ICommentService commentService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IDishOrderService dishOrderService;

    @RequestMapping(value="/get",method=RequestMethod.GET)
    public JSONObject checkDish(Integer hostID) {
        JSONObject jsonObj = new JSONObject();
        try {
            List<Dish> dishList = dishService.getDishInfo(hostID);
            List<JSONObject> tempList = new ArrayList<>();
            for (Dish dish : dishList) {
                JSONObject temp = new JSONObject();
                temp.put("dishID", dish.getDishID());
                temp.put("dishName", dish.getDishName());
                temp.put("price", dish.getPrice());
                temp.put("star", dish.getStar());
                temp.put("tags", dish.getTag());
                temp.put("picPath", dish.getPicturePath());
                temp.put("sales",dish.getSales());
                temp.put("introduction",dish.getIntroduction());
                tempList.add(temp);
            }
            jsonObj.put("status", 1);
            jsonObj.put("dishList", tempList);
        } catch (Exception ex) {
            jsonObj.put("status", 0);
            jsonObj.put("errmsg", "无对应菜品");
        }
        return jsonObj;
    }

    @RequestMapping(value="/detail",method=RequestMethod.GET)
    public JSONObject checkDetail(Integer dishID) {
        JSONObject jsonObj = new JSONObject();
        try {
            Dish dish = dishService.getDetail(dishID);
            List<Comment> commentList = commentService.getComments(dishID);
            List<JSONObject> tempList = new ArrayList<>();
            if (dish != null) {
                for (Comment comment : commentList) {
                    JSONObject temp = new JSONObject();
                    User user = userService.getUser(comment.getUserID());
                    temp.put("ID", comment.getCommentID());
                    temp.put("Name", user.getUserName());
                    temp.put("Rate", comment.getContent());
                    temp.put("Time", comment.getTime().toString());
                    temp.put("Url", user.getIconPath());
                    temp.put("Star", comment.getStar());
                    tempList.add(temp);
                }
                jsonObj.put("status", 1);
                jsonObj.put("imgList", dish.getPicturePath());
                jsonObj.put("dishName", dish.getDishName());
                jsonObj.put("price", dish.getPrice());
                jsonObj.put("sales", dish.getSales());
                jsonObj.put("star", dish.getStar());
                jsonObj.put("tagList", dish.getTag());
                jsonObj.put("evaluations", tempList);
            } else {
                jsonObj.put("status", 0);
                jsonObj.put("errmsg", "无详情");
            }
        } catch (Exception ex) {
            jsonObj.put("status", 0);
            jsonObj.put("errmsg", "无对应详情");
        }
        return jsonObj;
    }

    @RequestMapping(value="/add",method= RequestMethod.POST)
    public JSONObject addNewDish(Integer hostID, String dishName, Double price, String introduction, String picPathList) {
        JSONObject jsonObj = new JSONObject();
        try {
            boolean status = dishService.addDish(hostID, dishName, price, introduction, picPathList);
            if (status) {
                jsonObj.put("status", 1);
            }
            else{
                jsonObj.put("status", 0);
                jsonObj.put("errmsg", "添加菜品失败");
            }
        } catch (Exception ex) {
            jsonObj.put("status", 0);
            jsonObj.put("errmsg", "添加菜品失败");
        }
        return jsonObj;
    }

    @RequestMapping(value="/edit",method= RequestMethod.POST)
    public JSONObject editDishInfo(Integer dishID, String imgList, String dishName, Double price, String tagList) {
        JSONObject jsonObj = new JSONObject();
        try {
            boolean status = dishService.editDish(dishID,imgList,dishName,price,tagList);
            if (status) {
                jsonObj.put("status", 1);
            }
            else{
                jsonObj.put("status", 0);
                jsonObj.put("errmsg", "修改菜品信息失败");
            }
        } catch (Exception ex) {
            jsonObj.put("status", 0);
            jsonObj.put("errmsg", "修改菜品信息失败");
        }
        return jsonObj;
    }

    @RequestMapping(value="/addcomment",method= RequestMethod.POST)
    public JSONObject addNewComment(Integer userID, String comment,Integer star,Integer dishID,String orderID) {
        JSONObject jsonObj = new JSONObject();
        try {
            boolean status = commentService.addComment(userID,comment,star,dishID);
            dishService.addStar(dishID,star);
            dishOrderService.changestate(orderID,3);
            if (status) {
                jsonObj.put("status", 1);
            }
            else{
                jsonObj.put("status", 0);
                jsonObj.put("errmsg", "添加评论失败");
            }
        } catch (Exception ex) {
            jsonObj.put("status", 0);
            jsonObj.put("errmsg", "添加评论失败");
        }
        return jsonObj;
    }
}

