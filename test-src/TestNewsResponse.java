import com.tomato.wechat.response.NewsResponse;

/**
 * Created by wangronghua on 15/8/23.
 */
public class TestNewsResponse {
    public static void main(String[] args) {
        NewsResponse response = new NewsResponse();
        response.setFromUserName("from");
        response.setToUserName("to");
        response.add("test", "test", "test.pic", "test");
        response.add("test1", "test1", "test.pic", "test");
        System.out.println(response.toXml());
    }
}
