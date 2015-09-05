package com.tomato.wechat.servicemessage;

/**
 * Created by wangronghua on 14-3-19.
 */
public interface ServiceMessage {

    String FIELD_TITLE = "title";
    String FIELD_DESC = "description";
    String FIELD_URL = "url";
    String FIELD_PICURL = "picUrl";
    String FIELD_TOUSER = "touser";
    String FIELD_MSGTYPE = "msgtype";
    String FIELD_NEWS = "news";
    String FIELD_ARTICLES = "articles";
    String FIELD_TEXT = "text";
    String FIELD_CONTENT = "content";

    public String toJSON();
}
