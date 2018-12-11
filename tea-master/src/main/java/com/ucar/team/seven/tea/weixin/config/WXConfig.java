package com.ucar.team.seven.tea.weixin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 版权声明：Copyright(c) 2018 UCAR Inc. All Rights Reserved.
 *
 * @Author 李清灿(qingcan.li @ ucarinc.com)
 * @Date 22018/10/9 11:26
 * @Version
 * @Description 微信小程序的配置对象，spring会自动从wx.properties将相应的配置注入到这个对象中
 */
@Component
@PropertySource("classpath:wx.properties")
public class WXConfig {

    @Value("${AppID}")
    private String appID;

    @Value("${AppSecret}")
    private String appSecret;

    public String getAppID() {
        return appID;
    }

    public String getAppSecret() {
        return appSecret;
    }

    @Override
    public String toString() {
        return "WXConfig{" +
                "appID='" + appID + '\'' +
                ", appSecret='" + appSecret + '\'' +
                '}';
    }
}
