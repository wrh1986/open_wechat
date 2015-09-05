import com.tomato.wechat.servicemessage.impl.NewsServiceMessage;
import com.tomato.wechat.servicemessage.impl.TextServiceMessage;
import net.sf.json.JSONObject;

/**
 * Created by wangronghua on 15/8/23.
 */
public class TestServiceNewsMessage {

    public static void main(String[] args) {
        TextServiceMessage message = new TextServiceMessage("ada", "dadad");
        System.out.println(message.toJSON());

        NewsServiceMessage newsServiceMessage = new NewsServiceMessage("To");
        newsServiceMessage.add("test", "test", "test", "test");
        newsServiceMessage.add("test", "test", "test", "test");
        System.out.println(newsServiceMessage.toJSON());
    }
}
