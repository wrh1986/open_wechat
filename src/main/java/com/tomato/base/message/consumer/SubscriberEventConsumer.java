package com.tomato.base.message.consumer;

import com.tomato.wechat.request.SubscribeEvent;
import com.tomato.wechat.utils.WechatContext;

/**
 * Created by wangronghua on 15/8/3.
 */
public interface SubscriberEventConsumer extends MessageConsumer{

    public void consume(SubscribeEvent event);

}
