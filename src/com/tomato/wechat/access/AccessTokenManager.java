package com.tomato.wechat.access;

import com.tomato.base.account.WechatAccountManager;
import com.tomato.wechat.account.WechatAccount;
import com.tomato.wechat.utils.WechatContext;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tomato.wechat.HttpClientHelper;
import com.tomato.wechat.utils.Constants;
import com.tomato.wechat.utils.URLManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangronghua on 14-3-9.
 */
public class AccessTokenManager {
    protected final static Logger LOG = LoggerFactory.getLogger(AccessTokenManager.class);

    private static Map<String, Token> tokenMap = new HashMap<String, Token>();

    public synchronized static String getToken(String appId) {
        WechatAccount account = WechatAccountManager.get().getAccountByAppId(appId);

        if(null == account) return null;

        Token token = tokenMap.get(appId);
        if(null != token) {
            if(token.getExpireTimestamp() > System.currentTimeMillis()) {
                return token.getToken();
            }
        } else {
            token = new Token(null, 0l);
            tokenMap.put(appId, token);
        }
        String url = URLManager.getUrl_Accesstoken(appId, account.getAppSecret()) ;

        Map resultMap = HttpClientHelper.get(url);
        if(null != resultMap.get(Constants.ERR_CODE)) {
            LOG.error("Error while getting token from server, errcode:{};{}", String.valueOf(resultMap.get(Constants.ERR_CODE)), String.valueOf(resultMap.get(Constants.ERR_CODE)));
        } else if(null != resultMap.get(Constants.ACCESS_TOKEN)){
            token.setToken((String) resultMap.get(Constants.ACCESS_TOKEN));
            Integer expires = (Integer)resultMap.get(Constants.EXPIRES_IN);
            if(null != expires) {
                token.setExpireTimestamp(System.currentTimeMillis() + (expires - 1000) * 1000);
            } else {
                token.setExpireTimestamp(0);
            }
        }
        return token.getToken();
    }

    public synchronized static AccessToken getAccessToken(String appId, String code) {
        WechatAccount account = WechatAccountManager.get().getAccountByAppId(appId);

        if(null == account) return null;

        AccessToken token = null;
        String url = URLManager.getUrl_OAuthAccesstoken(appId, account.getAppSecret(), code);
        String resultString = HttpClientHelper.getResponseAsJSONString(url);
        JSONObject object = JSONObject.fromObject(resultString);
        if(null != object.get(Constants.ERR_CODE)) {
            LOG.error("Error while getting token from server, errcode:{};{}", String.valueOf(object.get(Constants.ERR_CODE)), String.valueOf(object.get(Constants.ERR_CODE)));
        } else {
            token = (AccessToken)JSONObject.toBean(object, AccessToken.class);
        }
        return token;
    }
}

class Token{
    private String token;
    private long expireTimestamp;

    public Token(String token, long timestamp) {
        this.token = token;
        this.expireTimestamp = timestamp;
    }

    public long getExpireTimestamp() {
        return expireTimestamp;
    }

    public void setExpireTimestamp(long expireTimestamp) {
        this.expireTimestamp = expireTimestamp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
