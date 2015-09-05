package com.tomato.wechat;

import com.tomato.base.loader.ComponentLoader;
import com.tomato.base.message.HandlerManager;
import com.tomato.base.message.MessageService;
import com.tomato.wechat.request.*;
import com.tomato.wechat.utils.Constants;
import com.tomato.wechat.utils.WechatContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangronghua on 14-3-10.
 */
public class DispatcherService {

    private static final String MSG_TYPE_TEXT = "text";
    private static final String MSG_TYPE_IMAGE = "image";
    private static final String MSG_TYPE_VOICE = "voice";
    private static final String MSG_TYPE_VIEDO = "video";
    private static final String MSG_TYPE_LOCATION = "location";
    private static final String MSG_TYPE_LINK = "link";
    private static final String MSG_TYPE_EVENT = "event";
    private static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    private static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    private static final String EVENT_TYPE_LOCATION = "LOCATION";
    private static final String EVENT_TYPE_CLICK = "CLICK";

//  private MessageHandler handler = new DefaultMessageHandler();

    public String process(WechatContext ctx, Map rMap) {
        String result = "";
        String msgType = (String)rMap.get(Constants.MSG_TYPE);
        BaseMessage request = null;
        if(MSG_TYPE_TEXT.equals(msgType)) {
            request = new TextRequest();
        } else if(MSG_TYPE_VOICE.equals(msgType)) {
            request = new VoiceRequest();
        } else if(MSG_TYPE_IMAGE.equals(msgType)) {
            request = new ImageRequest();
        } else if(MSG_TYPE_VIEDO.equals(msgType)) {
            request = new VideoRequest();
        } else if(MSG_TYPE_LINK.equals(msgType)) {
            request = new LinkRequest();
        } else if(MSG_TYPE_LOCATION.equals(msgType)) {
            request = new LocationRequest();
        } else if(MSG_TYPE_EVENT.equals(msgType)) {
            String event = (String)rMap.get(Constants.EVENT_TYPE);
            if(EVENT_TYPE_SUBSCRIBE.equals(event)) {
                request = new SubscribeEvent();
            } else if(EVENT_TYPE_UNSUBSCRIBE.equals(event)) {
                request = new UnSubscribeEvent();
            } else if(EVENT_TYPE_CLICK.equals(event)) {
                request = new ClickEvent();
            } else if(EVENT_TYPE_LOCATION.equals(event)) {
                request = new LocationEvent();
            }
        }

        if(request != null) {
            request.fill(rMap);
            request.setContext(ctx);
//          BaseMessage response = request.accept(handler);
            BaseMessage response = HandlerManager.get().handle(request);
            if(null != response) {
                response.setToUserName(request.getFromUserName());
                response.setFromUserName(request.getToUserName());
                response.setCreateTime(System.currentTimeMillis() / 1000);
                result = response.toXml();
            }

            //send request message to consumers
            MessageService.get().publish(request);
        }
        return result;
    }

    public static void main(String[] args) throws DocumentException, IOException {
        String inputStr = "<xml>\n" +
            "<ToUserName><![CDATA[toUser]]></ToUserName>\n" +
            "<FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
            "<CreateTime>12345678</CreateTime>\n" +
            "<MsgType><![CDATA[text]]></MsgType>\n" +
            "<Content><![CDATA[hello]]></Content>\n" +
            "</xml>";
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();

        // 从request中取得输入流
        InputStream inputStream = new StringBufferInputStream(inputStr);
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        DispatcherService service = new DispatcherService();
        ComponentLoader loader = new ComponentLoader();
        loader.load();
        System.out.println(service.process(new WechatContext(), map));
    }
}
