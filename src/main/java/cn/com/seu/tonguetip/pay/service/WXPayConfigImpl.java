package cn.com.seu.tonguetip.pay.service;

import com.github.wxpay.sdk.WXPayConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.InputStream;


@Component
@PropertySource("classpath:wxpay.properties")
public class WXPayConfigImpl implements WXPayConfig {

    @Value("${app_appId}")
    private String appID;

    @Value("${app_mchId}")
    private String mchID;

    @Value("${app_appSecret}")
    private String key;

    @Value("${app_notify_url}")
    private String notifyUrl;

    @Value("${spbill_create_ip}")
    private String spbillCreateIp;


    @Override
    public String getAppID() {
        return this.appID;
    }

    @Override
    public String getMchID() {
        return this.mchID;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 0;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 0;
    }

    public String getNotifyUrl(){
        return this.notifyUrl;
    }

    public String getspbillCreateIp(){
        return this.spbillCreateIp;
    }

}
