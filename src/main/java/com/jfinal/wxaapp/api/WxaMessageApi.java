/**
 * Copyright (c) 2011-2014, L.cm 卢春梦 (qq596392912@gmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.wxaapp.api;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.kit.ParaMap;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.jfinal.weixin.sdk.utils.JsonUtils;

/**
 * 客服接口-发消息
 *
 * @author L.cm
 */
public class WxaMessageApi {
    private static String customMessageUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";

    /**
     * 发送客服消息
     *
     * @param message 消息封装
     * @return ApiResult
     */
    private static ApiResult sendMsg(Map<String, Object> message) {
        String accessToken = WxaAccessTokenApi.getAccessTokenStr();
        String jsonResult = HttpUtils.post(customMessageUrl + accessToken, JsonUtils.toJson(message));
        return new ApiResult(jsonResult);
    }

    /**
     * 发送文本客服消息
     *
     * @param openId openId
     * @param text   文本消息
     * @return ApiResult
     */
    public static ApiResult sendText(String openId, String text) {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("touser", openId);
        json.put("msgtype", "text");

        Map<String, Object> textObj = new HashMap<String, Object>();
        textObj.put("content", text);

        json.put("text", textObj);
        return sendMsg(json);
    }

    /**
     * 发送图片消息
     *
     * @param openId  openId
     * @param mediaId 图片媒体id
     * @return ApiResult
     */
    public static ApiResult sendImage(String openId, String mediaId) {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("touser", openId);
        json.put("msgtype", "image");

        Map<String, Object> image = new HashMap<String, Object>();
        image.put("media_id", mediaId);

        json.put("image", image);
        return sendMsg(json);
    }

    /**
     * 发送图文链接
     *
     * @param openId      用户的 openId
     * @param title       消息标题
     * @param description 图文链接消息描述
     * @param url         跳转的链接
     * @param thumbUrl    图文链接消息的图片链接，支持 JPG、PNG 格式，较好的效果为大图 640 X 320，小图 80 X 80
     * @return {ApiResult}
     */
    public static ApiResult sendLink(String openId, String title, String description, String url, String thumbUrl) {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("touser", openId);
        json.put("msgtype", "link");
        json.put("link", ParaMap.create()
                .put("title", title)
                .put("description", description)
                .put("url", url)
                .put("thumb_url", thumbUrl)
                .getData()
        );
        return sendMsg(json);
    }

    /**
     * 发送 小程序卡片
     *
     * @param openId       用户的 openId
     * @param title        消息标题
     * @param pagePath     小程序的页面路径
     * @param thumbMediaId 小程序消息卡片的封面
     * @return {ApiResult}
     */
    public static ApiResult sendMiniProgramPage(String openId, String title, String pagePath, String thumbMediaId) {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("touser", openId);
        json.put("msgtype", "miniprogrampage");
        json.put("miniprogrampage", ParaMap.create()
                .put("title", title)
                .put("pagepath", pagePath)
                .put("thumb_media_id", thumbMediaId)
                .getData()
        );
        return sendMsg(json);
    }
}
