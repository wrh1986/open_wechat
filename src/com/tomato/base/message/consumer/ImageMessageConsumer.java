package com.tomato.base.message.consumer;

import com.tomato.wechat.request.ImageRequest;
import com.tomato.wechat.utils.WechatContext;

/**
 * Created by wangronghua on 15/8/3.
 */
public interface ImageMessageConsumer extends MessageConsumer {

    public void consume(ImageRequest message);

}
