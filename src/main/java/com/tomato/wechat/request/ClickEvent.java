package com.tomato.wechat.request;

import com.tomato.wechat.BaseEvent;
import com.tomato.wechat.utils.Constants;

import java.util.Map;

/**
 * Created by wangronghua on 14-3-15.
 */
public class ClickEvent extends BaseEvent {
  private String eventKey;

  public String getEventKey() {
    return eventKey;
  }

  public void setEventKey(String eventKey) {
    this.eventKey = eventKey;
  }

  public void fill(Map paraMap) {
    super.fill(paraMap);
    this.setEventKey(paraMap.get(Constants.EVENT_KEY).toString());
  }

}
