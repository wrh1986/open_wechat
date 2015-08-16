package com.tomato.wechat.servicemessage;

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
    JSONObject object = JSONObject.fromObject(message);
    HttpClientHelper.post(url, object.toString());
  }
}
