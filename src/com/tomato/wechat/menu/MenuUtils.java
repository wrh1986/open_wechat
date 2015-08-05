package com.tomato.wechat.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tomato.wechat.HttpClientHelper;
import com.tomato.wechat.access.AccessTokenManager;
import com.tomato.wechat.utils.Constants;
import com.tomato.wechat.utils.URLManager;
import com.tomato.wechat.utils.WechatContext;

import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by wangronghua on 14-3-18.
 */
public class MenuUtils {
  protected final static Logger LOG = LoggerFactory.getLogger(HttpClientHelper.class);

  public static String getRedirectUrl(WechatContext wctx, String url) throws UnsupportedEncodingException {
    return wctx.getDomainName() + "/com/tomato/wechat/redirect.action?url=" + URLEncoder.encode(url, "UTF-8");
  }

  public static String getOAuthUrl(WechatContext wctx, String url) throws UnsupportedEncodingException {
    return URLManager.getUrl_OAuthRedirect(wctx.getDomainName() + url, wctx.getAppId());
  }

  public static boolean create(String appId, InputStream in) {
    Map resultMap = HttpClientHelper.post(URLManager.getUrl_MenuCreate(AccessTokenManager.getToken(appId)), in);
    if(null != resultMap && (Integer)(resultMap.get(Constants.ERR_CODE)) == 0) {
      return true;
    }
    if(null != resultMap.get(Constants.ERR_CODE)) {
      LOG.error("Error while getting token from server, errcode:{};{}", String.valueOf(resultMap.get(Constants.ERR_CODE)), String.valueOf(resultMap.get(Constants.ERR_CODE)));
    }
    return false;
  }

  public static boolean create(String appId, File file) {
    try {
      InputStream inputStream = new FileInputStream(file);
      return create(appId, inputStream);
    } catch (FileNotFoundException e) {
      LOG.error("File not found", e);
      return false;
    }
  }

  public static boolean create(String appId, WechatMenu menu) {
    if(null != menu) {
      String jsonMenu = menu.toJson();
      try {
        return create(appId, new ByteArrayInputStream(jsonMenu.getBytes("UTF-8")));
      } catch(UnsupportedEncodingException e){
        LOG.error(e.getMessage());
        return false;
      }
    } else {
      LOG.error("Menu object is null!");
      return false;
    }

  }
}
