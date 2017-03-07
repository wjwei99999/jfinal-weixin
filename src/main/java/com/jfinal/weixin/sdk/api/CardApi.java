package com.jfinal.weixin.sdk.api;

import com.jfinal.kit.JMap;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.jfinal.weixin.sdk.utils.JsonUtils;

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
    
    
    private static String gethtmlMpnews = "https://api.weixin.qq.com/card/mpnews/gethtml?access_token=";
    
    /**
     * 图文消息群发卡券
     * @param cardId 必填 否 卡券ID。
     * @return {ApiResult}
     */
    public static ApiResult gethtmlMpnews(String cardId) {
        JMap data = JMap.create();
        if (StrKit.notBlank(cardId)) {
            data.set("card_id", cardId);
        }
        String jsonResult = HttpUtils.post(gethtmlMpnews + AccessTokenApi.getAccessTokenStr(), JsonUtils.toJson(data));
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
    
    private static String setPaycell = "https://api.weixin.qq.com/card/testwhitelist/set?access_token=";
    
    /**
     * 设置买单接口
     * @param cardId 卡券ID
     * @param isOpen 是否开启买单功能，填true/false
     * @return {ApiResult}
     */
    public static ApiResult setPaycell(String cardId, boolean isOpen) {
        JMap data = JMap.create("card_id", cardId).set("is_open", isOpen);
        String jsonResult = HttpUtils.post(setPaycell + AccessTokenApi.getAccessTokenStr(), JsonUtils.toJson(data));
        return new ApiResult(jsonResult);
    }
    
    private static String setSelfconsumecell = "https://api.weixin.qq.com/card/selfconsumecell/set?access_token=";
    
    /**
     * 设置自助核销接口
     * @param cardId 卡券ID
     * @param isOpen 是否开启买单功能，填true/false
     * @return {ApiResult}
     */
    public static ApiResult setSelfconsumecell(String cardId, boolean isOpen){
        return setSelfconsumecell(cardId, isOpen, false, false);
    }
    
    /**
     * 设置自助核销接口
     * @param cardId 卡券ID
     * @param isOpen 是否开启买单功能，填true/false
     * @param needVerifyCod 用户核销时是否需要输入验证码，填true/false，默认为false
     * @param needRemarkAmount 用户核销时是否需要备注核销金额，填true/false，默认为false
     * @return {ApiResult}
     */
    public static ApiResult setSelfconsumecell(String cardId, boolean isOpen, boolean needVerifyCod, boolean needRemarkAmount) {
        JMap data = JMap.create("card_id", cardId).set("is_open", isOpen).set("need_verify_cod", needVerifyCod)
                .set("need_remark_amount", needRemarkAmount);
        String jsonResult = HttpUtils.post(setSelfconsumecell + AccessTokenApi.getAccessTokenStr(), JsonUtils.toJson(data));
        return new ApiResult(jsonResult);
    }
}
