package com.ecommy.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 22:02 2018/4/25
 */
@Component
@Data
@ConfigurationProperties(prefix = "wechat")
public class PayConfig {
    private String appId;
    private String appSecret;
    private final String title = "米奇的口袋支付";
    private final String notifyUrl = "http://sell.natapp4.cc/sell/pay/notify";
    private final String tradeType = "JSAPI";
    private final String apiKey = "51VH03mx78j0gindrijxJYs6qJY76q8Y";
    private final String mchId = "1502953181";

    public String getApiKey() {
        return apiKey;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppSecret() {
        return appSecret;
    }
}
