package com.tomato.rushhour;

import com.tomato.base.component.AbstractComponent;


/**
 * Created by wangronghua on 15/8/16.
 */
public class Test2Plugin extends AbstractComponent {

    @Override
    public String getID() {
        return "TEST000002";
    }

    @Override
    public String getUniqueName() {
        return "TEST";
    }

    @Override
    public void init() {
        super.addConsumer(new TestTextMessageConsumer().setComponent(this));
    }
}
