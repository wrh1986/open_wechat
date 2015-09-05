package com.tomato.wechat.servicemessage;

import net.sf.json.JSONObject;

import java.util.Map;

/**
 * Created by wangronghua on 15/8/23.
 */
public class AbstractServiceMessage {

    protected Map result;

    public String toJSON() {
        if(null != result) {
            return JSONObject.fromObject(result).toString();
        }
        return "{}";
    }
}
