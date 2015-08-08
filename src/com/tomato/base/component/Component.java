package com.tomato.base.component;

import com.tomato.wechat.account.WechatAccount;

/**
 * Created by wangronghua on 15/8/2.
 */
public interface Component {

    /**
     * Component Id must be unique in the whole system
     * @return
     */
    public String getID();

    /**
     * unique name
     * @return
     */
    public String getUniqueName();

    /**
     * Init method, usually add consumer and handler to the component
     */
    public void init();

    /**
     * Tell whether component is enabled by different account
     * @param wechatAccount
     * @return
     */
    public boolean isEnabled(WechatAccount wechatAccount);

    /**
     * enable component flag by different account
     * @param wechatAccount
     * @return
     */
    public void enable(WechatAccount wechatAccount);

    /**
     * disable component flag by different account
     * @param wechatAccount
     * @return
     */
    public void disable(WechatAccount wechatAccount);
}
