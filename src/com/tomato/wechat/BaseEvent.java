package com.tomato.wechat;

import java.util.Map;

/**
 * Created by wangronghua on 14-3-15.
 */
public class BaseEvent extends BaseMessage {

  public String getEvent() {
    return Event;
  }

  public void setEvent(String event) {
    Event = event;
  }

  private String Event;

  public void fill(Map paraMap) {
    super.fill(paraMap);
    this.setEvent((String)paraMap.get("Event"));
  }
}
