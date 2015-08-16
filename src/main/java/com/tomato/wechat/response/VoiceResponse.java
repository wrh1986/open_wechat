package com.tomato.wechat.response;

import com.tomato.wechat.BaseMessage;
import com.tomato.wechat.utils.Constants;

/**
 * Created by wangronghua on 14-3-11.
 */
public class VoiceResponse extends BaseMessage {


  private com.tomato.wechat.response.Voice Voice;

  public void setMediaId(String mediaId) {
    this.setVoice(new com.tomato.wechat.response.Voice(mediaId));
  }

  public VoiceResponse() {
    this.setMsgType(Constants.MSG_TYPE_VOICE);
  }

  public String toXml() {
    xstream.alias("xml", this.getClass());
    xstream.alias("Voice", Voice.getClass());
    return xstream.toXML(this);
  }


  public com.tomato.wechat.response.Voice getVoice() {
    return Voice;
  }

  public void setVoice(com.tomato.wechat.response.Voice voice) {
    Voice = voice;
  }


}

class Voice{
  private String MediaId;

  public Voice(String MediaId) {
    this.MediaId = MediaId;
  }
  public String getMediaId() {
    return MediaId;
  }

  public void setMediaId(String MediaId) {
    this.MediaId = MediaId;
  }

}
