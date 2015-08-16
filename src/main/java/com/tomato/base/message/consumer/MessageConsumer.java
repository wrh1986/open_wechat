package com.tomato.base.message.consumer;

import com.tomato.base.component.Component;

/**
 * Created by wangronghua on 15/8/3.
 */
public interface MessageConsumer {

    /**
     * To support consumer binding to component
     * @return
     */
    public Component getComponent();

    public MessageConsumer setComponent(Component component);

}
