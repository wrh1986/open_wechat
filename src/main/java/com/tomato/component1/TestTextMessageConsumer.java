package com.tomato.component1;

import com.tomato.base.message.consumer.AbstractMessageConsumer;
import com.tomato.base.message.consumer.TextMessageConsumer;
import com.tomato.wechat.request.TextRequest;
import com.tomato.wechat.servicemessage.ServiceMessageUtils;
import com.tomato.wechat.servicemessage.impl.TextServiceMessage;

/**
 * Created by wangronghua on 15/8/5.
 */
public class TestTextMessageConsumer extends AbstractMessageConsumer implements TextMessageConsumer {

    @Override
    public void consume(TextRequest message) {
        System.out.println(Thread.currentThread().getId() + "Receiving message(Product):" + message.getContent());
        TextServiceMessage response = new TextServiceMessage(message.getFromUserName(), "Consume message(Product)");
        ServiceMessageUtils.sendMessage(message.getContext().getAppId(), response);
    }
}
