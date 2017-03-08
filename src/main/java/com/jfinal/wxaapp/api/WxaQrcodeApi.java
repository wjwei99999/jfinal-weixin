/**
 * Copyright (c) 2011-2014, L.cm 卢春梦 (qq596392912@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.wxaapp.api;

import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.kit.ParaMap;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.jfinal.weixin.sdk.utils.JsonUtils;

/**
 * 微信二维码api
 * @author L.cm
 *
 */
public class WxaQrcodeApi {
    // 文档地址:https://mp.weixin.qq.com/debug/wxadoc/dev/api/qrcode.html
    private static String createWxaQrcodeURL = "https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=";

    /**
     * 获取小程序页面二维码
     *
     * 通过该接口，仅能生成已发布的小程序的二维码。
     * 可以在开发者工具预览时生成开发版的带参二维码。
     * 带参二维码只有 100000 个，请谨慎调用。
     *
     * width 默认430
     * @param path 不能为空，最大长度 128 字节
     * @return ApiResult
     */
    public ApiResult createQrcode(String path) {
        return createQrcode(path, 430);
    }

    /**
     * 获取小程序页面二维码
     *
     * 通过该接口，仅能生成已发布的小程序的二维码。
     * 可以在开发者工具预览时生成开发版的带参二维码。
     * 带参二维码只有 100000 个，请谨慎调用。
     *
     * @param path 不能为空，最大长度 128 字节
     * @param width 默认430 二维码的宽度
     * @return ApiResult
     */
    public ApiResult createQrcode(String path, int width) {
        String url = createWxaQrcodeURL + WxaAccessTokenApi.getAccessTokenStr();
        ParaMap pm = ParaMap.create("path", path).put("width", String.valueOf(width));
        return new ApiResult(HttpUtils.post(url, JsonUtils.toJson(pm.getData())));
    }
}
