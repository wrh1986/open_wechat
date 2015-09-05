package com.tomato.base.message.consumer;

import com.tomato.base.component.Component;
import com.tomato.base.message.handler.MessageHandler;

/**
 * Created by wangronghua on 15/8/8.
 */
public abstract class AbstractMessageConsumer implements MessageConsumer{

    public MessageConsumer setComponent(Component component) {
        this.component = component;
        return this;
    }

    public Component getComponent() {
        return component;
    }

    protected Component component;

}
