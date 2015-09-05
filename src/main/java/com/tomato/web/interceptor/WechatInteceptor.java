package com.tomato.web.interceptor;

import com.tomato.base.account.WechatAccountManager;
import com.tomato.web.model.WechatObject;
import com.tomato.web.utils.Constants;
import com.tomato.wechat.access.AccessToken;
import com.tomato.wechat.access.AccessTokenManager;
import com.tomato.wechat.account.WechatAccount;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangronghua on 15/8/14.
 */
public class WechatInteceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String code = request.getParameter("code");
        String requestUrl = request.getRequestURL().toString();
        WechatAccount curAccount = null;
        for(WechatAccount account : WechatAccountManager.get().getAllAccounts()) {
            String webBaseUrl = account.getWebBaseUrl(); //should be unique accross accounts
            if(!webBaseUrl.endsWith("/")) {
                webBaseUrl += "/";
            }

            if(requestUrl.startsWith(webBaseUrl)) {
                curAccount = account;
                break;
            }
        }

        String openId = "test";
        if(null != curAccount) {
            //get openId from wechat server
            AccessToken token = AccessTokenManager.getAccessToken(curAccount.getAppId(), code);
            if(null != token) {
                openId = token.getOpenid();
            }
        }

        WechatObject wechatObject = new WechatObject();
        wechatObject.setOpenID(openId);
        wechatObject.setAccount(curAccount);
        request.setAttribute(Constants.WECHAT_OBJECT_ATTR_NAME, wechatObject);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
