package com.tomato.base.message.handler;

import com.tomato.wechat.BaseMessage;
import com.tomato.wechat.utils.WechatContext;

/**
 * Created by wangronghua on 15/8/3.
 */
public interface MessageHandler {

    public boolean isAccept(BaseMessage message);

    public BaseMessage handle(BaseMessage message);

}
