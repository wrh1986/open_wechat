package com.tomato.base.message;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;
import com.tomato.wechat.BaseMessage;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangronghua on 15/8/4.
 */
public class MessageService {

    private static final MessageService instance = new MessageService();
    private static final int MESSAGE_POOL_SIZE = 1024;
    private static final int EVENT_HANDLER_SIZE = 10;
    private Disruptor<MessageEvent> disruptor;

    private MessageService(){
        Executor executor = Executors.newFixedThreadPool((Runtime.getRuntime().availableProcessors() + 1 ));
        disruptor = new Disruptor(new EventFactory<MessageEvent>() {
            @Override
            public MessageEvent newInstance() {
                return new MessageEvent();
            }
        }, MESSAGE_POOL_SIZE, executor);
        EventHandler[] dispatchers = new EventHandler[EVENT_HANDLER_SIZE];
        for(int index = 0; index < dispatchers.length; index ++) {
            dispatchers[index] = new EventHandler<MessageEvent>(){

                @Override
                public void onEvent(MessageEvent event, long sequence, boolean endOfBatch) throws Exception {
                    ConsumerDispatcher.get().consume(event.getMessage());
                }
            };
        }
        disruptor.handleEventsWith(dispatchers);
        disruptor.start();
    }

    public static MessageService get(){
        return instance;
    }

    public void publish(final BaseMessage message) {
        disruptor.publishEvent(new EventTranslator<MessageEvent>() {
            @Override
            public void translateTo(MessageEvent event, long sequence) {
                event.setMessage(message);
            }
        });
    }


}
