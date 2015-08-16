package com.tomato.wechat.servicemessage;

import net.sf.json.JSONObject;

/**
 * Created by wangronghua on 14-3-19.
 */
public class TextServiceMessage implements ServiceMessage {

  public String getTouser() {
    return touser;
  }

  public void setTouser(String touser) {
    this.touser = touser;
  }

  public String getMsgtype() {
    return msgtype;
  }

  public MessageText getText() {
    return text;
  }

  public void setText(MessageText text) {
    this.text = text;
  }

  public void setContext(String context) {
    text= new MessageText();
    text.setContent(context);
  }

  private String touser;
  private String msgtype = "text";
  private MessageText text;

  public TextServiceMessage(String touser, String content) {
    this.touser = touser;
    this.setContext(content);
  }

  public static void main(String[] args) {
    TextServiceMessage message = new TextServiceMessage("ada", "dadad");
    MessageText t = new MessageText();
    t.setContent("adad");
    String x = JSONObject.fromObject(message).toString();
    System.out.print(x);
  }
}
