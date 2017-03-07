package com.jfinal.weixin.sdk.api;

import com.jfinal.weixin.sdk.utils.HttpUtils;

/**
 * 会员卡相关接口
 * Created by L.cm on 2016/6/16.
 */
public class CardApi {
    private static String cardCreateUrl = "https://api.weixin.qq.com/card/create?access_token=";
    
    /**
     * 创建会员卡接口
     * @param jsonStr JSON数据
     * @return {ApiResult}
     */
    public static ApiResult create(String jsonStr) {
        String jsonResult = HttpUtils.post(cardCreateUrl + AccessTokenApi.getAccessTokenStr(), jsonStr);
        return new ApiResult(jsonResult);
    }
    
    private static String createQrcodeCard = "https://api.weixin.qq.com/card/qrcode/create?access_token=";
    
    /**
     * 创建二维码接口
     * @param jsonStr JSON数据
     * @return {ApiResult}
     */
    public static ApiResult createQrcode(String jsonStr) {
        String jsonResult = HttpUtils.post(createQrcodeCard + AccessTokenApi.getAccessTokenStr(), jsonStr);
        return new ApiResult(jsonResult);
    }
    
    private static String createLandingPageCard = "https://api.weixin.qq.com/card/landingpage/create?access_token=";
    
    /**
     * 创建货架接口
     * @param jsonStr JSON数据
     * @return {ApiResult}
     */
    public static ApiResult createLandingPage(String jsonStr) {
        String jsonResult = HttpUtils.post(createLandingPageCard + AccessTokenApi.getAccessTokenStr(), jsonStr);
        return new ApiResult(jsonResult);
    }
    
    private static String setTestWhiteList = "https://api.weixin.qq.com/card/testwhitelist/set?access_token=";
    
    /**
     * 设置测试白名单
     * @param jsonStr JSON数据
     * @return {ApiResult}
     */
    public static ApiResult setTestWhiteList(String jsonStr) {
        String jsonResult = HttpUtils.post(setTestWhiteList + AccessTokenApi.getAccessTokenStr(), jsonStr);
        return new ApiResult(jsonResult);
    }
    
    // https://mp.weixin.qq.com/advanced/wiki?t=t=resource/res_main&id=mp1444738727
    
    
}
