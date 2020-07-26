package cn.com.seu.tonguetip.server.controller;

import cn.com.seu.tonguetip.server.entity.Dish;
import cn.com.seu.tonguetip.server.entity.DishOrder;
import cn.com.seu.tonguetip.server.service.IDishOrderService;
import cn.com.seu.tonguetip.server.service.IDishService;
import cn.com.seu.tonguetip.server.service.impl.DishOrderServiceImpl;
import cn.com.seu.tonguetip.server.service.impl.WXPayConfigImpl;
import cn.com.seu.tonguetip.server.service.WXPayService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wxpay.sdk.WXPayUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/codeurl",method = RequestMethod.POST)
    public JSONObject CodeUrl(Double price,String dishName,String orderID,Integer dishID,
                              String PS,Integer userID,Integer number){
        JSONObject jsonObject = new JSONObject();
        try {
            dishOrderService.deleteOrder(userID);
            DishOrder dishOrder = dishOrderService.newDishOrder(userID,dishID,number,price,orderID,PS);
            dishOrder.setHostID(dishService.gethostID(dishID));
            dishOrder.setState(2);
            dishOrderService.save(dishOrder);
            String codeUrl = wxPayService.WXCodeUrl(price,dishName,orderID);
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
        String orderID = map.get("out_trade_no");
        String price = map.get("total_fee");

        // 验签
        boolean signatureValid = WXPayUtil.isSignatureValid(strXml, config.getKey());

        // 验证订单信息
        QueryWrapper<DishOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("OrderID",orderID);
        DishOrder dishOrder = dishOrderService.getOne(wrapper);
        Double p = dishOrder.getPrices() * 100;
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
                dishOrderService.changestate(orderID,0);

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
    public JSONObject getOrderState(String orderID) {
        JSONObject jsonObject = new JSONObject();
        try {
            QueryWrapper<DishOrder> wrapper = new QueryWrapper<>();
            wrapper.eq("OrderID", orderID);
            DishOrder dishOrder = dishOrderService.getOne(wrapper);
            jsonObject.put("status",1);
            jsonObject.put("state",dishOrder.getState());
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("status",0);
        }
        return jsonObject;
    }
}
