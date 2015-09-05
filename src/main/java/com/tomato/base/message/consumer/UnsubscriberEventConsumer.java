package com.tomato.base.message.consumer;

import com.tomato.wechat.request.UnSubscribeEvent;
import com.tomato.wechat.utils.WechatContext;

/**
 * Created by wangronghua on 15/8/3.
 */
public interface UnsubscriberEventConsumer extends MessageConsumer {

    public void consume(UnSubscribeEvent event);

}
