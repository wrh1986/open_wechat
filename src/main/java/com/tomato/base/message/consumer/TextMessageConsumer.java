package com.tomato.base.message.consumer;

import com.tomato.wechat.request.TextRequest;
import com.tomato.wechat.utils.WechatContext;

/**
 * Created by wangronghua on 15/8/3.
 */
public interface TextMessageConsumer extends MessageConsumer {

    public void consume(TextRequest message);
}
