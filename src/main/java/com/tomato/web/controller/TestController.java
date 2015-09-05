package com.tomato.web.controller;

import com.tomato.web.model.WechatObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wangronghua on 15/8/15.
 */
@Controller
@RequestMapping(value = "/{wechatid}/wechatweb")
public class TestController {

    @RequestMapping(value = "/test")
    @ResponseBody
    public String test(@RequestParam String code, WechatObject wechat, HttpServletRequest request) {
        System.out.println("code : " + wechat.getOpenID());
        System.out.println("openid : " + wechat.getOpenID());
        return "openid : " + wechat.getOpenID();
    }
}
