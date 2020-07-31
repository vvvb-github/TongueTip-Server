package cn.com.seu.tonguetip.server.controller;

import cn.com.seu.tonguetip.server.entity.Dish;
import cn.com.seu.tonguetip.server.entity.DishOrder;
import cn.com.seu.tonguetip.server.entity.Params;
import cn.com.seu.tonguetip.server.service.*;
import cn.com.seu.tonguetip.server.service.impl.DishOrderServiceImpl;
import cn.com.seu.tonguetip.server.service.impl.WXPayConfigImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wxpay.sdk.WXPayUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pay")
@CrossOrigin(origins = "*",allowCredentials = "true")
public class WXPayController {

    @Autowired
    private WXPayService wxPayService;

    @Autowired
    private IDishOrderService dishOrderService;

    @Autowired
    private IDishService dishService;

    @Autowired
    private WXPayConfigImpl config;

    @Autowired
    private IHostService hostService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/codeurl",method = RequestMethod.POST)
    public JSONObject newOrder(@RequestBody Params params){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(params);
        JSONObject jsonObject = new JSONObject();
        Double prices = 0.0;
        try {
            dishOrderService.deleteOrder(params.userID);
            for(Integer i=0;i<params.cnt;++i) {
                DishOrder dishOrder = dishOrderService.newDishOrder(params.userID, params.dishID[i],
                        params.number[i], params.price[i], params.orderID[i], params.PS[i]);
                dishOrder.setHostID(dishService.gethostID(params.dishID[i]));
                dishOrder.setState(2);
                dishOrderService.save(dishOrder);
                prices += params.price[i];
            }
            String codeUrl = wxPayService.WXCodeUrl(prices,params.userID,params.orderID[0]);
            jsonObject.put("status",1);
            jsonObject.put("url",codeUrl);
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("status",0);
        }
        return jsonObject;
    }

    @RequestMapping("/payOrder")
    public void payOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取微信回调信息
        InputStream inputStream = request.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuffer sb = new StringBuffer();
        // 读取回调信息
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        in.close();
        inputStream.close();
        String strXml = sb.toString();
        Map<String, String> map = WXPayUtil.xmlToMap(strXml);

        System.out.println(strXml);

        // 获取业务信息
        Integer userID = Integer.valueOf(map.get("product_id"));
        String price = map.get("total_fee");

        // 验签
        boolean signatureValid = WXPayUtil.isSignatureValid(strXml, config.getKey());

        // 验证订单信息
        QueryWrapper<DishOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("UserID",userID);
        wrapper.eq("State",2);
        List<DishOrder> lst = dishOrderService.list(wrapper);
        Double p = 0.0;
        for(DishOrder order : lst){
            p += order.getPrices();
        }
        Integer i_p = p.intValue();
        if(!i_p.toString().equals(price)){
            signatureValid = false;
        }

        PrintWriter writer = response.getWriter();
        // 签名是否正确
        if (signatureValid) {
            // 回调信息是否成功
            if ("SUCCESS".equals(map.get("result_code"))) {
                // 补充以下代码, 根据实际业务完善逻辑
                for(DishOrder order : lst){
                    dishOrderService.changestate(order.getOrderID(),0);
                }

                System.out.println("********付款成功********");
                // 通知微信订单处理成功
                String noticeStr = setXML("SUCCESS", "SUCCESS");
                writer.write(noticeStr);
                writer.flush();
            }
        } else {
            // 通知微信订单处理失败
            String noticeStr = setXML("FAIL", "FAIL");
            writer.write(noticeStr);
            writer.flush();
        }
    }

    private String setXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
    }

    @RequestMapping(value = "/state",method = RequestMethod.GET)
    public JSONObject getOrderState(Integer userID) {
        JSONObject jsonObject = new JSONObject();
        try {
            QueryWrapper<DishOrder> wrapper = new QueryWrapper<>();
            wrapper.eq("UserID", userID);
            wrapper.eq("State",2);
            jsonObject.put("cnt",dishOrderService.count(wrapper));
            jsonObject.put("status",1);
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("status",0);
        }
        return jsonObject;
    }
}
