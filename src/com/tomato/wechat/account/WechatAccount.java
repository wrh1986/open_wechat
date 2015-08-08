package com.tomato.wechat.account;

import com.tomato.base.component.Component;
import com.tomato.wechat.access.AccessTokenManager;
import com.tomato.wechat.menu.MenuUtils;
import com.tomato.wechat.menu.WechatMenu;
import org.apache.commons.lang.StringUtils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangronghua on 15/8/5.
 */
@XmlRootElement
public class WechatAccount {

    private String appId;
    private String appToken;
    private String appSecret;
    private String domainName;

    private Map<String, Component> componentMap = new ConcurrentHashMap<>();

    @XmlElement
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @XmlElement
    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    @XmlElement
    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    @XmlElement
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

    public String getAccessToken() {
        return AccessTokenManager.getToken(this.appId);
    }

    public boolean isComponentEnabled(Component component) {
        if(null != component && StringUtils.isNotEmpty(component.getID())) {
            if(componentMap.containsKey(component.getID())){
                return true;
            } else {
                return false;
            }
        }
        /**
         * if input component is null, always return true because of default value
         */
        return true;
    }

    public boolean enableComponent(Component component) {
        if(null != component && StringUtils.isNotEmpty(component.getID())) {
            componentMap.put(component.getID(), component);
            return true;
        }
        return false;
    }

    public boolean disableComponent(Component component) {
        if(null != component && StringUtils.isNotEmpty(component.getID())) {
            componentMap.remove(component.getID());
            return true;
        }
        return false;
    }
}
