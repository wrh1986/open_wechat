package com.tomato.wechat.servicemessage.impl;

import com.tomato.wechat.servicemessage.AbstractServiceMessage;
import com.tomato.wechat.servicemessage.ServiceMessage;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangronghua on 14-3-19.
 */
public class TextServiceMessage extends AbstractServiceMessage implements ServiceMessage {

    private static final String MSGTYPE = "text";

    public TextServiceMessage(String touser, String content) {
        result = new HashMap();
        result.put(FIELD_TOUSER, touser);
        result.put(FIELD_MSGTYPE, MSGTYPE);
        Map contentMap = new HashMap();
        contentMap.put(FIELD_CONTENT, content);
        result.put(FIELD_TEXT, contentMap);
    }
}


