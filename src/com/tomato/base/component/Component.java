package com.tomato.base.component;

/**
 * Created by wangronghua on 15/8/2.
 */
public interface Component {

    public String getUniqueName();

    public void init();

    public String isEnabled();
    public String enable();
    public String disable();
}
