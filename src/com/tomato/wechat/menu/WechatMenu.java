package com.tomato.wechat.menu;

import java.util.List;

/**
 * Created by wangronghua on 14-3-18.
 */
public class WechatMenu {

  public List<WechatButton> getButton() {
    return button;
  }

  public void setButton(List<WechatButton> button) {
    this.button = button;
  }

  private List<WechatButton> button;

  public String toJson() {
    StringBuilder result = new StringBuilder();
    result.append("{\"button\":[");
    if(null != button) {
      int length = button.size();
      for(int index = 0; index < length; index ++) {
        if(index > 0) {
          result.append(",");
        }
        result.append(button.get(index).toJson());
      }
    }
    result.append("]}");
    return  result.toString();
  }

}
