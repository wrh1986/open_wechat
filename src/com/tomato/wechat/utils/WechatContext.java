package com.tomato.wechat.utils;

import com.tomato.wechat.account.WechatAccount;

/**
 * Created by wangronghua on 14-3-19.
 */
public class WechatContext {

//  private static SystemSettingBusiness ssb = (SystemSettingBusiness) SingleBusinessPoolManager.getBusObj(BusTieConstant.BUS_CPATH_SYSTEMSETTING);

    private String appId;
    private String domainName;
    private WechatAccount wechatAccount;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public WechatAccount getWechatAccount() {
        return wechatAccount;
    }

    public void setWechatAccount(WechatAccount wechatAccount) {
        this.wechatAccount = wechatAccount;
    }


    public static void init() {
//        MessageBus.get().addHandler(new SubscribeEventHandler());
//        MessageBus.get().addHandler(new UnSubscriberEventHandler());
//        MessageBus.get().addHandler(new LocationEventHandler());
    }

    public static String getWelcomeMsg() {
        String result = "欢迎使用wechat服务平台!";
//    SystemSettingBean bean = ssb.getLeaf();
//    if(null != bean) {
//      result = bean.getWelcomeMsg();
//    }
        return result;
    }

}
