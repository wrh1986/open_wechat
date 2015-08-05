package com.tomato.wechat;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangronghua on 14-3-10.
 */
public class HttpClientHelper {
  protected final static Logger LOG = LoggerFactory.getLogger(HttpClientHelper.class);

  public static Map get(String url) {
    Map resultMap = new HashMap();
    CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpGet httpget = new HttpGet(url);
    try {
      HttpResponse response = httpclient.execute(httpget);
      String resultStr = EntityUtils.toString(response.getEntity(), "UTF-8");
      JSONObject object = JSONObject.fromObject(resultStr);
      resultMap = (Map)JSONObject.toBean(object, Map.class);
    } catch (IOException e) {
      LOG.error("",e);
    } finally {
      try {
        httpclient.close();
      } catch (IOException e) {
        LOG.error("",e);
      }
    }
    return resultMap;
  }

  public static String getResponseAsJSONString(String url) {
    String resultStr = "{}";
    CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpGet httpget = new HttpGet(url);
    try {
      HttpResponse response = httpclient.execute(httpget);
      resultStr = EntityUtils.toString(response.getEntity(), "UTF-8");
    } catch (IOException e) {
      LOG.error("URL request error:{}", url);
      LOG.error("",e);
    } finally {
      try {
        httpclient.close();
      } catch (IOException e) {
        LOG.error("",e);
      }
    }
    return resultStr;
  }

  public static Map post(String url, InputStream inputStream) {
    Map resultMap = new HashMap();
    CloseableHttpClient client = HttpClients.createDefault();
    HttpPost post = new HttpPost(url);
    HttpEntity entity = new InputStreamEntity(inputStream, ContentType.create(ContentType.TEXT_PLAIN.getMimeType(), "utf-8"));

    try {
      post.setEntity(entity);
      HttpResponse response = client.execute(post);
      String resultStr = EntityUtils.toString(response.getEntity());
      JSONObject object = JSONObject.fromObject(resultStr);
      resultMap = (Map)JSONObject.toBean(object, Map.class);
    } catch (IOException e) {
      LOG.error("",e);
    } finally {
      try {
        client.close();
      } catch (IOException e) {
        LOG.error("",e);
      }
      return resultMap;
    }
  }

  public static Map post(String url, String content) {
    Map resultMap = new HashMap();
    CloseableHttpClient client = HttpClients.createDefault();

    try {
      HttpPost post = new HttpPost(url);

      HttpEntity entity = new StringEntity(content, "utf-8");

      post.setEntity(entity);
      HttpResponse response = client.execute(post);
      String resultStr = EntityUtils.toString(response.getEntity());
      JSONObject object = JSONObject.fromObject(resultStr);
      resultMap = (Map)JSONObject.toBean(object, Map.class);
    } catch (UnsupportedEncodingException e) {
      LOG.error("",e);
    } catch (IOException e) {
      LOG.error("",e);
    } finally {
      try {
        client.close();
      } catch (IOException e) {
        LOG.error("",e);
      }
      return resultMap;
    }
  }
}
