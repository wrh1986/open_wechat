package com.tomato.base.component;

import com.tomato.base.message.ConsumerDispatcher;
import com.tomato.base.message.HandlerManager;
import com.tomato.base.message.MessageService;
import com.tomato.base.message.consumer.MessageConsumer;
import com.tomato.base.message.handler.MessageHandler;
import com.tomato.wechat.account.WechatAccount;

/**
 * Created by wangronghua on 15/8/4.
 */
public abstract class AbstractComponent implements Component {

    protected boolean enable = true;

    public void addConsumer(MessageConsumer consumer) {
        ConsumerDispatcher.get().addConsumer(consumer);
    }

    public void addHandler(MessageHandler handler) {
        HandlerManager.get().addHandler(handler);
    }

    public void disable() {
        this.enable = false;
    }

    public void enable() {
        this.enable = true;
    }

    @Override
    public boolean isEnabled(WechatAccount wechatAccount) {
        if(null != wechatAccount) {
            return enable && wechatAccount.isComponentEnabled(this);
        }
        return enable;
    }

    @Override
    public void enable(WechatAccount wechatAccount) {
        if(null != wechatAccount) {
            wechatAccount.enableComponent(this);
        }
    }

    @Override
    public void disable(WechatAccount wechatAccount) {
        if(null != wechatAccount) {
            wechatAccount.disableComponent(this);
        }
    }
}
