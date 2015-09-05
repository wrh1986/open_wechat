package com.tomato.base.message;

import com.tomato.base.message.handler.MessageHandler;
import com.tomato.wechat.BaseMessage;
import com.tomato.wechat.utils.WechatContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangronghua on 15/8/3.
 */
public class HandlerManager {

    private static final HandlerManager instance = new HandlerManager();

    private HandlerManager(){}

    public static HandlerManager get() {
        return instance;
    }

    private List<MessageHandler> handlers = new ArrayList<>();

    public BaseMessage handle(BaseMessage message) {

        for(MessageHandler handler : handlers) {
            if(handler.isAccept(message)) {
                return handler.handle(message);
            }
        }
        return null;
    }

    public void addHandler(MessageHandler handler) {
        handlers.add(handler);
    }
}
