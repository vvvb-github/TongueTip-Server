package cn.com.seu.tonguetip.admin.controller;


import cn.com.seu.tonguetip.admin.entity.UserCheck;
import cn.com.seu.tonguetip.admin.service.IUserCheckService;
import cn.com.seu.tonguetip.admin.service.IUserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author five_dumplings
 * @since 2020-07-22
 */
@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = {"http://localhost:8080"},allowCredentials = "true")
public class AdminController {

    @Autowired
    private IUserCheckService checkService;

    @Autowired
    private IUserService userService;

    private String IP = "http://192.168.0.110";

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public JSONObject getChecks(){
        JSONObject jsonObject = new JSONObject();
        try {
            List<UserCheck> checkList = checkService.getCheckList();
            List<JSONObject> lst = new ArrayList<>();
            for (UserCheck check:checkList) {
                JSONObject ck = new JSONObject();
                ck.put("m_checkID",check.getCheckID());
                ck.put("m_picPath",check.getPictruePath());
                lst.add(ck);
            }
            jsonObject.put("status",1);
            jsonObject.put("checkList",lst);
        }catch (Exception e){
            jsonObject.put("status",0);
            jsonObject.put("errmsg","获取审核列表失败");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/accept",method = RequestMethod.GET)
    public JSONObject Accept(Integer checkID){
        JSONObject jsonObject = new JSONObject();
        try{
            Integer userID = checkService.getUserIDByCheckID(checkID);
            userService.acceptCheck(userID);
            checkService.deleteCheckByID(checkID);
            jsonObject.put("status",1);
        }catch (Exception e){
            jsonObject.put("status",0);
            jsonObject.put("errmsg","审核批准失败");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/reject",method = RequestMethod.GET)
    public JSONObject Reject(Integer checkID){
        JSONObject jsonObject = new JSONObject();
        try{
            Integer userID = checkService.getUserIDByCheckID(checkID);
            userService.rejectCheck(userID);
            checkService.deleteCheckByID(checkID);
            jsonObject.put("status",1);
        }catch (Exception e){
            jsonObject.put("status",0);
            jsonObject.put("errmsg","审核拒绝失败");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/image",method = RequestMethod.POST)
    public JSONObject UploadIMG(@RequestParam("file") MultipartFile multipartFile){
        System.out.println(multipartFile);
        JSONObject jsonObject = new JSONObject();
        try {
            String serverPath = System.getProperty("user.dir") + "/image/";
            String uuid = UUID.randomUUID()	.toString();
            String suffixName = multipartFile.getOriginalFilename().
                    substring(multipartFile.getOriginalFilename().lastIndexOf("."));
            String path = serverPath + uuid + suffixName;
            File newFile = new File(path);
            multipartFile.transferTo(newFile);
            jsonObject.put("status",1);
            path = IP + "/image/" + uuid + suffixName;
            jsonObject.put("url",path);
        }catch (Exception e){
            jsonObject.put("status",0);
            jsonObject.put("errmsg",e.getMessage());
        }
        return jsonObject;
    }
}
