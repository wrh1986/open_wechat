import com.tomato.base.loader.ComponentLoader;
import com.tomato.wechat.account.WechatAccount;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangronghua on 15/8/8.
 */
public class TestWechatAccountSerialization {

    public static void main(String[] args) {
        List<WechatAccount> list = new ArrayList<>();
        Accounts accounts = new Accounts();
        WechatAccount account = new WechatAccount();
        account.setAppId("testapp");
        account.setDomainName("http://localhost/wechat");
        account.setAppToken("test");
        account.setAppSecret("secret");

        list.add(account) ;
        accounts.setWechatAccountList(list);
//        System.out.println(toXML(accounts));

        ComponentLoader loader = new ComponentLoader();
        List<String> components = new ArrayList<>();
        components.add("com.test.Component1");
        components.add("com.test.Component2");
        components.add("com.test.Component3");
        loader.setComponents(components);
        System.out.println(toXML(loader));
    }

    public static String toXML(Object obj) {
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// //编码格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式化生成的xml串
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);// 是否省略xm头声明信息
            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromXML(String xml, Class<T> valueType) {
        try {
            JAXBContext context = JAXBContext.newInstance(valueType);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
