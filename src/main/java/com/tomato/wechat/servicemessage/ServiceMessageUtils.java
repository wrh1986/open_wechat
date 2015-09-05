package com.tomato.wechat.servicemessage;

import com.tomato.wechat.servicemessage.impl.NewsServiceMessage;
import com.tomato.wechat.servicemessage.impl.TextServiceMessage;
import net.sf.json.JSONObject;
import com.tomato.wechat.HttpClientHelper;
import com.tomato.wechat.access.AccessTokenManager;
import com.tomato.wechat.utils.URLManager;

/**
 * Created by wangronghua on 14-3-19.
 */
public class ServiceMessageUtils {

    public static void sendMessage(String appId, ServiceMessage message) {
        String url = URLManager.getUrl_ServiceMessage(AccessTokenManager.getToken(appId));
        HttpClientHelper.post(url, message.toJSON());
    }

}
