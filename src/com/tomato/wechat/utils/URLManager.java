package com.tomato.wechat.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by wangronghua on 14-3-18.
 */
public class URLManager {

  private static final String URL_ACCESSTOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

  private static final String URL_OAUTH_ACCESSTOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

  private static final String URL_MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
  private static final String URL_MENU_GET = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=%s";
  private static final String URL_MENU_DELETE = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=%s";

  private static final String URL_OAUTH_REDIRECT = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=STATE#wechat_redirect";

  private static final String URL_SERVICE_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s";

  private static final String URL_GET_USERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";

  public static String getUrl_Accesstoken(String appID, String appSecret) {
    return String.format(URL_ACCESSTOKEN, appID, appSecret);
  }

  public static String getUrl_OAuthAccesstoken(String appID, String appSecret, String code) {
    return String.format(URL_OAUTH_ACCESSTOKEN, appID, appSecret, code);
  }

  public static String getUrl_MenuCreate(String accessToken) {
    return String.format(URL_MENU_CREATE, accessToken);
  }

  public static String getUrl_MenuGet(String accessToken) {
    return String.format(URL_MENU_GET, accessToken);
  }

  public static String getUrl_MenuDelete(String accessToken) {
    return String.format(URL_MENU_DELETE, accessToken);
  }

  public static String getUrl_OAuthRedirect(String url, String appID) throws UnsupportedEncodingException {
    return String.format(URL_OAUTH_REDIRECT, appID, URLEncoder.encode(url, "utf-8"), "snsapi_base");
  }

  public static String getUrl_OAuthRedirect(String url, String appID, String scope) throws UnsupportedEncodingException {
    return String.format(URL_OAUTH_REDIRECT, appID, URLEncoder.encode(url, "utf-8"), scope);
  }

  public static String getUrl_ServiceMessage(String accessToken) {
    return String.format(URL_SERVICE_MESSAGE, accessToken);
  }

  public static String getUrl_GetUserInfo(String accessToken, String openID) {
    return String.format(URL_GET_USERINFO, accessToken, openID);
  }
}
