package com.tomato.base.message;

import com.lmax.disruptor.EventHandler;
import com.tomato.base.message.consumer.*;
import com.tomato.wechat.BaseMessage;
import com.tomato.wechat.request.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangronghua on 15/8/3.
 */
public class ConsumerDispatcher{

    private static final ConsumerDispatcher instance = new ConsumerDispatcher();

    private List<SubscriberEventConsumer> subscriberEventConsumers = new ArrayList<>();
    private List<UnsubscriberEventConsumer> unsubscriberEventConsumers = new ArrayList<>();
    private List<TextMessageConsumer> textMessageConsumers = new ArrayList<>();
    private List<VoiceMessageConsumer> voiceMessageConsumers = new ArrayList<>();
    private List<ImageMessageConsumer> imageMessageConsumers = new ArrayList<>();
    private List<LocationEventConsumer> locationEventConsumers = new ArrayList<>();


    private ConsumerDispatcher(){}

    public static ConsumerDispatcher get(){
        return instance;
    }

    public void consume(BaseMessage message) throws Exception {
        if (message instanceof SubscribeEvent) {
            for(SubscriberEventConsumer consumer : subscriberEventConsumers) {
                consumer.consume((SubscribeEvent)message);
            }
        } else if(message instanceof UnSubscribeEvent) {
            for(UnsubscriberEventConsumer consumer : unsubscriberEventConsumers) {
                consumer.consume((UnSubscribeEvent) message);
            }
        } else if(message instanceof TextRequest) {
            for(TextMessageConsumer consumer : textMessageConsumers) {
                consumer.consume((TextRequest) message);
            }
        } else if(message instanceof VoiceRequest) {
            for(VoiceMessageConsumer consumer : voiceMessageConsumers) {
                consumer.consume((VoiceRequest) message);
            }
        } else if(message instanceof ImageRequest) {
            for(ImageMessageConsumer consumer : imageMessageConsumers) {
                consumer.consume((ImageRequest) message);
            }
        } else if(message instanceof LocationEvent) {
            for(LocationEventConsumer consumer : locationEventConsumers) {
                consumer.consume((LocationEvent) message);
            }
        }
    }

    public void addConsumer(MessageConsumer consumer) {
        if(consumer instanceof SubscriberEventConsumer) {
            subscriberEventConsumers.add((SubscriberEventConsumer) consumer);
        } else if(consumer instanceof UnsubscriberEventConsumer) {
            unsubscriberEventConsumers.add((UnsubscriberEventConsumer) consumer);
        } else if(consumer instanceof TextMessageConsumer) {
            textMessageConsumers.add((TextMessageConsumer) consumer);
        } else if(consumer instanceof VoiceMessageConsumer) {
            voiceMessageConsumers.add((VoiceMessageConsumer) consumer);
        } else if(consumer instanceof ImageMessageConsumer) {
            imageMessageConsumers.add((ImageMessageConsumer) consumer);
        } else if(consumer instanceof LocationEventConsumer) {
            locationEventConsumers.add((LocationEventConsumer) consumer);
        }
    }
}
