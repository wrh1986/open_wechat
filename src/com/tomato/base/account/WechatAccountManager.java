package com.tomato.base.account;

import com.tomato.wechat.account.WechatAccount;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;

/**
 * Created by wangronghua on 15/8/5.
 */
public class WechatAccountManager {

    private static final WechatAccountManager instance = new WechatAccountManager();

    private WechatAccountManager() {}

    public static WechatAccountManager get(){
        return instance;
    }

    private HashMap<String, WechatAccount> accountMap = new HashMap<>();
    private HashMap<String, WechatAccount> domainMap = new HashMap<>();

    public WechatAccount getAccountByAppId(String appId) {
        if(StringUtils.isNotEmpty(appId)) {
            return accountMap.get(appId);
        }
        return null;
    }

    public WechatAccount getAccountByDomainName(String domainName) {
        if(StringUtils.isNotEmpty(domainName)) {
            return domainMap.get(domainName);
        }
        return null;
    }

    public void addAccount(WechatAccount account) {
        if(null != account && StringUtils.isNotEmpty(account.getAppId())) {
            accountMap.put(account.getAppId(), account);
        }

        if(null != account && StringUtils.isNotEmpty(account.getDomainName())) {
            domainMap.put(account.getDomainName(), account);
        }
    }

    public void removeAccount(WechatAccount account) {
        if(null != account && StringUtils.isNotEmpty(account.getAppId())) {
            accountMap.remove(account.getAppId());
        }

        if(null != account && StringUtils.isNotEmpty(account.getDomainName())) {
            domainMap.remove(account.getDomainName());
        }
    }

}
