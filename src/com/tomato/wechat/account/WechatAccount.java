package com.tomato.wechat.account;

import com.tomato.wechat.menu.MenuUtils;
import com.tomato.wechat.menu.WechatMenu;

import java.io.File;
import java.io.InputStream;

/**
 * Created by wangronghua on 15/8/5.
 */
public class WechatAccount {

    private String appId;
    private String appToken;
    private String appSecret;
    private String domainName;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public void createMenu(WechatMenu menu){
        MenuUtils.create(this.appId, menu);
    }

    public void createMenu(InputStream menu){
        MenuUtils.create(this.appId, menu);
    }

    public void createMenu(File menu){
        MenuUtils.create(this.appId, menu);
    }

}
