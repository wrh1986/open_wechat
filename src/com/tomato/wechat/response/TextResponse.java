package com.tomato.wechat.response;

import com.tomato.wechat.BaseMessage;
import com.tomato.wechat.utils.Constants;

/**
 * Created by wangronghua on 14-3-11.
 */
public class TextResponse extends BaseMessage {

  private String Content;
  public String getContent() {
    return Content;
  }

  public void setContent(String content) {
    Content = content;
  }

  public TextResponse() {
    this.setMsgType(Constants.MSG_TYPE_TEXT);
  }

  public String toXml() {
    xstream.alias("xml", this.getClass());
    return xstream.toXML(this);
  }
}
