package com.tomato.base.component;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangronghua on 15/8/2.
 */
public class ComponentManager {

    private static final ComponentManager instance = new ComponentManager();

    private List<Component> componentList = new ArrayList<>();

    private ComponentManager() {}

    public static ComponentManager get() {
        return instance;
    }

    public void addComponent(Component c) {
        c.init();
        componentList.add(c);
    }

    public Component getComponent(String uniqueName) {
        if(StringUtils.isNotEmpty(uniqueName)) {
            for(Component c : componentList) {
                if(uniqueName.equals(c.getUniqueName())) {
                    return c;
                }
            }
        }
        return null;
    }

    public void removeComponent() {
        return;
    }
}
