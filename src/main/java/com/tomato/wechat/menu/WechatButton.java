package com.tomato.wechat.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangronghua on 14-3-18.
 */
public class WechatButton {

  private String name;
  private String type;  // view:浏览型， click:事件型， null:父菜单
  private String key;
  private String url;
  private List<WechatButton> sub_button;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public List<WechatButton> getSub_button() {
    return sub_button;
  }

  public void setSub_button(List<WechatButton> sub_button) {
    this.sub_button = sub_button;
  }

  public  void addSubButton(WechatButton button) {
    if(null != sub_button) {
      sub_button.add(button);
    } else  {
      sub_button = new ArrayList<WechatButton>();
      sub_button.add(button);
    }
  }

  public String toJson(){
    StringBuilder result = new StringBuilder();
    result.append("{");
    result.append("\"name\":\"").append(name).append("\"");
    result.append(",");
    if("click".equals(type)) {
      result.append("\"type\":\"").append(type).append("\"");
      result.append(",");
      result.append("\"key\":\"").append(key).append("\"");
    } else if("view".equals(type)) {
      result.append("\"type\":\"").append(type).append("\"");
      result.append(",");
      result.append("\"url\":\"").append(url).append("\"");
    } else {
      if(null != sub_button) {
        result.append("\"sub_button\":[");
        int length = sub_button.size();
        for(int index = 0; index < length; index ++) {
          if(index > 0) {
            result.append(",");
          }
          WechatButton button = sub_button.get(index);
          result.append(button.toJson());
        }
        result.append("]");
      }
    }
    result.append("}");
    return result.toString();
  }
}
