package cn.com.seu.tonguetip.server.controller;


import cn.com.seu.tonguetip.server.entity.User;
import cn.com.seu.tonguetip.server.entity.UserCheck;
import cn.com.seu.tonguetip.server.service.IUserCheckService;
import cn.com.seu.tonguetip.server.service.IUserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.applet.resources.MsgAppletViewer_es;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author five_dumplings
 * @since 2020-07-23
 */
@RestController
@RequestMapping("/profile")
@CrossOrigin(origins = "*",allowCredentials = "true")
public class UserController {
    @Autowired
    private IUserService userService;
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public JSONObject loginUser(String userPhone,String userPassword,Integer userType){
        JSONObject jsonObj = new JSONObject();
        try{
            if(userPhone==null||userPhone.equals(""))
            {
                jsonObj.put("status","30002");
                jsonObj.put("errmsg","用户电话号码为空");
                return jsonObj;
            }
            if(userPassword==null||userPassword.equals(""))
            {
                jsonObj.put("status","30003");
                jsonObj.put("errmsg","密码为空");
                return jsonObj;
            }
            User user =userService.getUser(userPhone,userPassword,userType);
            jsonObj.put("status",1);
            jsonObj.put("userID",user.getUserID());
            jsonObj.put("userName",user.getUserName());
            jsonObj.put("userPriority",user.getPriority());
            jsonObj.put("IconPath",user.getIconPath());
        }catch (Exception ex){
            jsonObj.put("status",0);
            jsonObj.put("errmsg","用户登录失败");
        }
        return jsonObj;
    }
    @Autowired
    private IUserCheckService userCheckService;
    @RequestMapping(value="/register",method = RequestMethod.POST)
    //phone怎么判断
    public JSONObject registerUser(Integer userType,String userName,String userPhone,String userPassword,String repeatPassword,String picPath){
        JSONObject jsonObj = new JSONObject();

            if(userName==null||userName.equals(""))
            {
                jsonObj.put("status","30001");
                jsonObj.put("errmsg","用户名为空");
                return jsonObj;
            }
            if(userPhone==null||userPhone.equals(""))
            {
                jsonObj.put("status","30002");
                jsonObj.put("errmsg","用户电话号码为空");
                return jsonObj;
            }
            if(userPassword==null||userPassword.equals(""))
            {
                jsonObj.put("status","30003");
                jsonObj.put("errmsg","密码为空");
                return jsonObj;
            }
            if(userType==1)
            {
                //System.out.println("33");
                User user2=userService.setUser(userType,userName,userPhone,userPassword);
                //System.out.println("44");
                if(!user2.getPhoneNumber().equals("奶萌兔"))
                {
                    jsonObj.put("status","1");
                    jsonObj.put("userID",user2.getUserID());
                    jsonObj.put("userPriority",user2.getPriority());
                    jsonObj.put("iconPath",user2.getIconPath());
                }
                else
                {
                    jsonObj.put("status","0");
                    jsonObj.put("errmsg","顾客注册失败");
                    return jsonObj;
                }
            }
            else
            {
                if(picPath==null||picPath.equals(""))
                {
                    jsonObj.put("status","30004");
                    jsonObj.put("errmsg","商家没有执照图片");
                    return jsonObj;
                }
                User user3=userService.setUser(0,userName,userPhone,userPassword);
                if(!user3.getPhoneNumber().equals("奶萌兔"))
                {
                    userCheckService.setUserCheck(user3.getUserID(),picPath);
                    jsonObj.put("status","1");
                    jsonObj.put("userID",user3.getUserID());
                    jsonObj.put("userPriority",user3.getPriority());
                    jsonObj.put("iconPath",user3.getIconPath());
                }
                else
                {
                    jsonObj.put("status","0");
                    jsonObj.put("errmsg","商家注册失败");
                    return jsonObj;
                }
            }


        return jsonObj;
    }

    @RequestMapping(value="/edit",method = RequestMethod.POST)
    public JSONObject editUser(Integer userID,String userName,String userPhone,String userPassword,String picPath)
    {
        JSONObject jsonObj = new JSONObject();
        try{
            if(userService.setUser_2(userID,userName,userPhone,userPassword,picPath))
            {
                jsonObj.put("status","1");
            }
            else
            {
                jsonObj.put("status","2");
                return jsonObj;
            }
        }catch (Exception ex){
            jsonObj.put("status","0");
        }
        return jsonObj;
    }
}