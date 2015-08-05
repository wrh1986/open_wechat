package com.tomato.wechat.access;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by wangronghua on 14-3-9.
 */
public class AccessValidator {

  private String signature;
  private String token;
  private String timestamp;
  private String nonce;

  private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
                       '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

  public AccessValidator(String signature, String token, String timestamp, String nonce){
    this.signature = signature;
    this.token = token;
    this.timestamp = timestamp;
    this.nonce = nonce;
  }

  public boolean validate() {
    List<String> paraList = new ArrayList<String>();
    paraList.add(token);
    paraList.add(timestamp);
    paraList.add(nonce);
    Collections.sort(paraList);
    StringBuilder str = new StringBuilder();
    for(String s: paraList) {
      str.append(s);
    }
    String encodeString = "";
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
      messageDigest.update(str.toString().getBytes());
      //todo we need to know whether we should getFormattedText
      encodeString = getFormattedText(messageDigest.digest());
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return encodeString.equals(signature);
  }

  private static String getFormattedText(byte[] bytes) {
    int len = bytes.length;
    StringBuilder buf = new StringBuilder(len * 2);
    for (int j = 0; j < len; j++) {
      buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
      buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
    }
    return buf.toString();
  }
}
