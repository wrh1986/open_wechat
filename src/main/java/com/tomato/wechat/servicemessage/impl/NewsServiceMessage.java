package com.tomato.wechat.servicemessage.impl;

import com.tomato.wechat.servicemessage.AbstractServiceMessage;
import com.tomato.wechat.servicemessage.ServiceMessage;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangronghua on 15/8/23.
 */
public class NewsServiceMessage extends AbstractServiceMessage implements ServiceMessage {

    private static final String MSG_TYPE = "news";

    private Map news;
    private List articles;


    public void add(String title, String description, String url, String picUrl) {
        Map article = new HashMap();
        article.put(FIELD_TITLE, title);
        article.put(FIELD_DESC, description);
        article.put(FIELD_URL, url);
        article.put(FIELD_PICURL, picUrl);
        articles.add(article);
    }

    public void setTouser(String touser) {
        result.put(FIELD_TOUSER, touser);
    }

    public NewsServiceMessage(String touser) {
        result = new HashMap();
        news = new HashMap();
        articles = new ArrayList();
        result.put(FIELD_MSGTYPE, MSG_TYPE);
        result.put(FIELD_NEWS, news);
        news.put(FIELD_ARTICLES, articles);
        this.setTouser(touser);
    }

    public boolean hasArticle() {
        return this.articles != null && this.articles.size() > 0;
    }
}

