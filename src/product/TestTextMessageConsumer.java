package product;

import com.tomato.base.message.consumer.TextMessageConsumer;
import com.tomato.wechat.request.TextRequest;

/**
 * Created by wangronghua on 15/8/5.
 */
public class TestTextMessageConsumer implements TextMessageConsumer {
    @Override
    public void consume(TextRequest message) {
        System.out.println(Thread.currentThread().getId() + "Receiving message:" + message.getContent());
    }
}
