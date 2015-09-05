package com.tomato.web.model;

import com.tomato.wechat.account.WechatAccount;
import com.tomato.wechat.servicemessage.ServiceMessageUtils;
import com.tomato.wechat.servicemessage.impl.NewsServiceMessage;
import com.tomato.wechat.servicemessage.impl.TextServiceMessage;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * An wechat object to hold the necessary information when processing http request from wechat
 * Created by wangronghua on 15/9/4.
 */
public class WechatObject {

    public static final String NEWS_TITLE = "TITLE";
    public static final String NEWS_DESC = "DESC";
    public static final String NEWS_URL = "URL";
    public static final String NEWS_PIC_URL = "PICURL";

    private String openID;
    private String unionID;
    private WechatAccount account;

    public WechatAccount getAccount() {
        return account;
    }

    public void setAccount(WechatAccount account) {
        this.account = account;
    }

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    /**
     * Send text message to wechat client
     * @param text
     * @return true means the message is sent, false means message was not sent
     */
    public boolean responseText(String text) {
        if(null != account && StringUtils.isNotEmpty(openID)) {
            TextServiceMessage response = new TextServiceMessage(this.openID, text);
            ServiceMessageUtils.sendMessage(account.getAppId(), response);
            return true;
        }
        return false;
    }

    /**
     * Send news message to wechat client
     * @param newsList inside the list there must be maps which is containing keys: NEWS_TITLE, NEWS_DESC, NEWS_URL, NEWS_PIC_URL
     * @return true means the message is sent, false means message was not sent
     */
    public boolean responseNews(List<Map<String, String>> newsList) {
        if(null != account && StringUtils.isNotEmpty(openID) && null != newsList) {
            NewsServiceMessage newsServiceMessage = new NewsServiceMessage(openID);
            for(Map<String, String> news : newsList) {
                String title = news.get(NEWS_TITLE);
                String desc = news.get(NEWS_DESC);
                String url = news.get(NEWS_URL);
                String picURL = news.get(NEWS_PIC_URL);
                if(StringUtils.isNotEmpty(title) && StringUtils.isNotEmpty(url)) {
                    newsServiceMessage.add(title, desc, url, picURL);
                }
            }
            if(newsServiceMessage.hasArticle()) {
                ServiceMessageUtils.sendMessage(account.getAppId(), newsServiceMessage);
                return true;
            }
        }
        return false;
    }

}
