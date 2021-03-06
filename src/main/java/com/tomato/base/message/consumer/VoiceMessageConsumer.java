package com.tomato.base.message.consumer;

import com.tomato.wechat.request.VoiceRequest;
import com.tomato.wechat.utils.WechatContext;

/**
 * Created by wangronghua on 15/8/3.
 */
public interface VoiceMessageConsumer extends MessageConsumer {

    public void consume(VoiceRequest message);

}
