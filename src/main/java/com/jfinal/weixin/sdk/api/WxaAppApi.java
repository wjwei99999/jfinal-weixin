package com.jfinal.weixin.sdk.api;

import com.jfinal.weixin.sdk.kit.ParaMap;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.jfinal.weixin.sdk.utils.JsonUtils;

/**
 * 微信小程序系列api，预留
 * @author L.cm
 *
 */
public class WxaAppApi {
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
     */
    public static ApiResult createQrcode(String path) {
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
     */
    public static ApiResult createQrcode(String path, int width) {
        String url = createWxaQrcodeURL + AccessTokenApi.getAccessTokenStr();
        ParaMap pm = ParaMap.create("path", path).put("width", String.valueOf(width));
        return new ApiResult(HttpUtils.post(url, JsonUtils.toJson(pm.getData())));
    }
}
