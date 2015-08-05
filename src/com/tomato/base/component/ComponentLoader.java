package com.tomato.base.component;

import product.ProductPlugin;

/**
 * Created by wangronghua on 15/8/2.
 */
public class ComponentLoader {


    public void load() {
        ComponentManager.get().addComponent(new ProductPlugin());
        //...
    }
}
