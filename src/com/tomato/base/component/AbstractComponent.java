package com.tomato.base.component;

import com.tomato.base.message.ConsumerDispatcher;
import com.tomato.base.message.MessageService;
import com.tomato.base.message.consumer.MessageConsumer;
import com.tomato.base.message.handler.MessageHandler;

/**
 * Created by wangronghua on 15/8/4.
 */
public abstract class AbstractComponent implements Component {

    public void addConsumer(MessageConsumer consumer) {
        ConsumerDispatcher.get().addConsumer(consumer);
    }

    public void addHandler(MessageHandler handler) {

    }
}
