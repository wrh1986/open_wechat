package com.tomato.base.message.consumer;

import com.tomato.wechat.request.LocationEvent;
import com.tomato.wechat.utils.WechatContext;

/**
 * Created by wangronghua on 15/8/3.
 */
public interface LocationEventConsumer extends MessageConsumer {

    public void consume(LocationEvent event);

}
