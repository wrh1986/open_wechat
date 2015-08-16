package com.tomato.base.message;

import com.tomato.wechat.BaseMessage;

/**
 * Created by wangronghua on 15/8/5.
 */
public class MessageEvent {
    public BaseMessage getMessage() {
        return message;
    }

    public void setMessage(BaseMessage message) {
        this.message = message;
    }

    private BaseMessage message;
}
