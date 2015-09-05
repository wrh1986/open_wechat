package com.tomato.wechat.response;

import com.tomato.wechat.BaseMessage;
import com.tomato.wechat.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangronghua on 14-3-11.
 */
public class NewsResponse extends BaseMessage {

    private int ArticleCount = 0;
    List<Article> Articles = new ArrayList<>();

    public NewsResponse() {
        super.setMsgType(Constants.MSG_TYPE_NEWS);
    }

    public String toXml() {
        xstream.alias("xml", this.getClass());
        xstream.alias("item", Article.class);
        return xstream.toXML(this);
    }

    public void add(String title, String description, String picUrl, String url) {
        Articles.add(new Article(title, description, picUrl, url));
        ArticleCount ++;
    }
}


class Article {
    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        this.PicUrl = picUrl;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        this.Url = url;
    }

    public Article(String title, String description, String picUrl, String url) {
        this.Title = title;
        this.Description = description;
        this.PicUrl = picUrl;
        this.Url = url;
    }

    private String Title;
    private String Description;
    private String PicUrl;
    private String Url;
}
