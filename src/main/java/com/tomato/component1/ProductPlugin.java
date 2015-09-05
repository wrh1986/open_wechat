package com.tomato.component1;

import com.tomato.base.component.AbstractComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangronghua on 15/8/2.
 */
public class ProductPlugin extends AbstractComponent {

    @Override
    public String getID() {
        return "TEST000001";
    }

    @Override
    public String getUniqueName() {
        return "商品";
    }

    public void getMenus() {

    }

    @Override
    public void init() {
//        super.addConsumer(new TestTextMessageConsumer());
        super.addConsumer(new TestTextMessageConsumer().setComponent(this));
        super.addHandler(new TestTextMessageHandler());
    }

    public List<String> getManagedUrl(){
        List<String> result = new ArrayList<>();
        return result;
    }
}
