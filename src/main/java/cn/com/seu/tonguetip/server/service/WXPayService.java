package cn.com.seu.tonguetip.server.service;

import cn.com.seu.tonguetip.server.service.impl.WXPayConfigImpl;
import com.github.wxpay.sdk.WXPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class WXPayService {

    @Autowired
    private WXPayConfigImpl config;

    public String WXCodeUrl(Double price,Integer userID,String orderID) throws Exception{
        WXPay wxpay =  new WXPay(config);

        // 异步通知地址
        String notifyUrl = config.getNotifyUrl();

        Map<String, String> data = new HashMap<>();
        // 商品描述
        data.put("body", "舌尖");
        // 商户订单号
        data.put("out_trade_no", orderID);
        // 标价金额
        price = price * 100;
        Integer p = price.intValue();
        data.put("total_fee", p.toString());
        // 产品id
        data.put("attach",userID.toString());
        data.put("product_id", userID.toString());
        // 终端IP:调用微信支付API服务器的IP地址
        String spbillCreateIp = config.getspbillCreateIp();
        data.put("spbill_create_ip", spbillCreateIp);
        // 交易类型:此处指定为扫码支付
        data.put("trade_type", "NATIVE");
        // 异步通知 url
        data.put("notify_url", notifyUrl);
        Map<String, String> resp = null;
        try {
            resp = wxpay.unifiedOrder(data);
            System.out.println("-----------start---------------");
            System.out.println(resp);
            System.out.println("-----------end---------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String codeUrl = resp.get("code_url");
        return codeUrl;
    }
}
