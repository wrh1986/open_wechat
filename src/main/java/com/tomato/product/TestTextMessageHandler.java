package com.tomato.product;

import com.tomato.base.message.handler.MessageHandler;
import com.tomato.wechat.BaseMessage;
import com.tomato.wechat.request.TextRequest;
import com.tomato.wechat.response.TextResponse;

/**
 * Created by wangronghua on 15/8/15.
 */
public class TestTextMessageHandler implements MessageHandler {
    @Override
    public boolean isAccept(BaseMessage message) {
        return true;
    }

    @Override
    public BaseMessage handle(BaseMessage message) {
        TextResponse response = new TextResponse();
        if(message instanceof TextRequest) {
            String content = "收到:" + ((TextRequest)message).getContent();
            response.setContent(content);
        } else {
            response.setContent("非文本消息:" + message.getMsgType());
        }
        return response;
    }
}
