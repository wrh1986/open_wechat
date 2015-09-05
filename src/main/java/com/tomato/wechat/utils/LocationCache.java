package com.tomato.wechat.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tomato.wechat.request.LocationEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangronghua on 14-4-19.
 */
public class LocationCache {
  private static Logger log = LoggerFactory.getLogger(LocationCache.class);

  private static ConcurrentHashMap<String, LocationEvent> dataMap = new ConcurrentHashMap<String, LocationEvent>();
  private static final long TIME_OUT_VALUE = 300000;

    private static List<String> keysList = new ArrayList<String>();
    private LocationCache(){}

  static {
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        while(!Thread.interrupted()) {
          try {
            Thread.sleep(TIME_OUT_VALUE);
            synchronized (keysList) {
              long currTime = System.currentTimeMillis();
              Iterator<Map.Entry<String, LocationEvent>> it = dataMap.entrySet().iterator();
              while(it.hasNext()) {
                Map.Entry<String, LocationEvent> entry = it.next();
                String key = entry.getKey();
                LocationEvent event = entry.getValue();
                if( (currTime - event.getCreateTime()*1000) > TIME_OUT_VALUE) {
                  keysList.add(key);
                }
              }

              for(String key : keysList) {
                log.info("Clear location for com.tomato.wechat user({})", key);
                dataMap.remove(key);
              }
              keysList.clear();
            }
          } catch (InterruptedException e) {
            break;
          }
        }
      }
    }) ;
    thread.setDaemon(true);
    thread.start();
  }

  public static void putLocation(LocationEvent locationEvent) {
    log.info("Save location for com.tomato.wechat user({}), Latitude:{}, Longitude:{}, Precision:{}",
        locationEvent.getFromUserName(), locationEvent.getLatitude(),
        locationEvent.getLongitude(), locationEvent.getPrecision());
    synchronized (keysList) {
      dataMap.put(locationEvent.getFromUserName(), locationEvent);
    }
  }

  public static LocationEvent getLocation(String openID) {
    return dataMap.get(openID);
  }
}
