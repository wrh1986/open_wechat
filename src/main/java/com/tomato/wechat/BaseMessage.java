package com.tomato.wechat;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.tomato.wechat.utils.WechatContext;

import java.io.Writer;
import java.util.Map;

/**
 * Created by wangronghua on 14-3-11.
 */
public class BaseMessage {

    private String ToUserName;
    private String FromUserName;
    private long CreateTime;
    private String MsgType;
    /**
     * 扩展xstream，使其支持CDATA块
     */
    protected static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("unchecked")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    public void fill(Map paraMap) {
        this.setToUserName((String)paraMap.get("ToUserName"));
        this.setFromUserName((String)paraMap.get("FromUserName"));
        this.setCreateTime(Long.valueOf((String) paraMap.get("CreateTime")));
        this.setMsgType((String)paraMap.get("MsgType"));
    }

    public String toXml() {
        return "not supported";
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public WechatContext getContext() {
        return context;
    }

    public void setContext(WechatContext context) {
        this.context = context;
    }

    @XStreamOmitField
    private WechatContext context;
}
