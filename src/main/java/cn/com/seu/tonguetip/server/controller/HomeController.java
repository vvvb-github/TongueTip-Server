package cn.com.seu.tonguetip.server.controller;


import cn.com.seu.tonguetip.server.entity.Dish;
import cn.com.seu.tonguetip.server.entity.Host;
import cn.com.seu.tonguetip.server.service.IDishService;
import cn.com.seu.tonguetip.server.service.IHostService;
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
 * @since 2020-07-23
 */
@RestController
@RequestMapping("/home")
@CrossOrigin(origins = "*",allowCredentials = "true")
public class HomeController {

    @Autowired
    private IDishService dishService;

    @Autowired
    private IHostService hostService;

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public JSONObject get()
    {
        JSONObject jsonObj = new JSONObject();
        try{
            jsonObj.put("status",1);
            List<Host> AllHost = hostService.getAllHost();
            List<JSONObject> lista = new ArrayList<>();
            for (Host i:AllHost)
            {
                JSONObject j = new JSONObject();
                j.put("hostID",i.getHostID());
                j.put("hostName",i.getHostName());
                j.put("phone",i.getPhoneNumber());
                j.put("location",i.getLocation());
                j.put("star",i.getStar());
                j.put("picPath",i.getPicturePath());
                lista.add(j);
            }
            jsonObj.put("hostList",lista);
            List<Host> RecommendHost = hostService.getRecommentHost();
            List<JSONObject> listb = new ArrayList<>();
            for (Host i:RecommendHost)
            {
                JSONObject j = new JSONObject();
                j.put("hostID",i.getHostID());
                j.put("hostName",i.getHostName());
                j.put("phone",i.getPhoneNumber());
                j.put("location",i.getLocation());
                j.put("star",i.getStar());
                j.put("picPath",i.getPicturePath());
                listb.add(j);
            }
            jsonObj.put("recommendList",listb);
            List<Host> RankList = hostService.getRankList();
            List<JSONObject> listc = new ArrayList<>();
            for (Host i:RankList)
            {
                JSONObject j = new JSONObject();
                j.put("hostID",i.getHostID());
                j.put("hostName",i.getHostName());
                j.put("phone",i.getPhoneNumber());
                j.put("location",i.getLocation());
                j.put("star",i.getStar());
                j.put("picPath",i.getPicturePath());
                listc.add(j);
            }
            jsonObj.put("rankList",listc);
            List<Dish> HotDish = dishService.getHotDish();
            List<JSONObject> listd = new ArrayList<>();
            for (Dish i:HotDish)
            {
                JSONObject j = new JSONObject();
                j.put("dishID",i.getDishID());
                j.put("dishName",i.getDishName());
                j.put("location",hostService.getLocation(i.getHostID()));
                j.put("picPath",i.getPicturePath());
                listd.add(j);
            }
            jsonObj.put("hotList",listd);
        }catch (Exception ex){
            jsonObj.put("status",0);
        }
        return jsonObj;
    }

    @RequestMapping(value = "/hostbydish",method = RequestMethod.GET)
    public JSONObject hostbydish(Integer dishID)
    {
        JSONObject jsonObj = new JSONObject();
        try{
            Integer hostID = dishService.gethostID(dishID);
            Host host = hostService.getHost(hostID);
            jsonObj.put("status",1);
            jsonObj.put("hostID",hostID);
            jsonObj.put("hostName",host.getHostName());
            jsonObj.put("hostPhone",host.getPhoneNumber());
            jsonObj.put("location",host.getLocation());
            jsonObj.put("star",host.getStar());
            jsonObj.put("picPath",host.getPicturePath());
            jsonObj.put("introduction",host.getIntroduction());
        }catch (Exception ex){
            jsonObj.put("status",0);
        }
        return jsonObj;
    }

    @RequestMapping(value = "/hostbyuser",method = RequestMethod.GET)
    public JSONObject hostbyuser(Integer userID)
    {
        JSONObject jsonObj = new JSONObject();
        try{
            Host host = hostService.hostbyuser(userID);
            jsonObj.put("status",1);
            jsonObj.put("hostID",host.getHostID());
            jsonObj.put("hostName",host.getHostName());
            jsonObj.put("hostPhone",host.getPhoneNumber());
            jsonObj.put("location",host.getLocation());
            jsonObj.put("star",host.getStar());
            jsonObj.put("picPath",host.getPicturePath());
            jsonObj.put("introduction",host.getIntroduction());
        }catch (Exception ex){
            jsonObj.put("status",0);
        }
        return jsonObj;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public JSONObject edit(Integer hostID,String hostName,String phone,String location,String introduction,String picPath)
    {
        JSONObject jsonObj = new JSONObject();
        try{
            hostService.editHost(hostID,hostName,phone,location,introduction,picPath);
            jsonObj.put("status",1);
        }catch (Exception ex){
            jsonObj.put("status",0);
        }
        return jsonObj;
    }

    @RequestMapping(value = "/hostbyid",method = RequestMethod.GET)
    public JSONObject hostbyid(Integer hostID)
    {
        JSONObject jsonObj = new JSONObject();
        try{
            Host host = hostService.hostbyid(hostID);
            jsonObj.put("status",1);
            jsonObj.put("hostName",host.getHostName());
            jsonObj.put("hostPhone",host.getPhoneNumber());
            jsonObj.put("location",host.getLocation());
            jsonObj.put("star",host.getStar());
            jsonObj.put("picPath",host.getPicturePath());
            jsonObj.put("introduction",host.getIntroduction());
        }catch (Exception ex){
            jsonObj.put("status",0);
        }
        return jsonObj;
    }
}
