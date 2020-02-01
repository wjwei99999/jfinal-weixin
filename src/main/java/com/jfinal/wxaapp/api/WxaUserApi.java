/**
 * Copyright (c) 2011-2014, L.cm 卢春梦 (qq596392912@gmail.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.wxaapp.api;

import com.jfinal.kit.HashKit;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.encrypt.WxaBizDataCrypt;
import com.jfinal.weixin.sdk.kit.ParaMap;
import com.jfinal.weixin.sdk.kit.PaymentKit;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.jfinal.wxaapp.WxaConfig;
import com.jfinal.wxaapp.WxaConfigKit;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信小程序用户api接口
 *
 * @author L.cm
 */
public class WxaUserApi {
    private static String jsCode2sessionUrl = "https://api.weixin.qq.com/sns/jscode2session";

    /**
     * 获取sessionKey
     *
     * @param jsCode 登录时获取的 code
     * @return ApiResult
     */
    public static ApiResult getSessionKey(String jsCode) {
        WxaConfig wc = WxaConfigKit.getWxaConfig();
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", wc.getAppId());
        params.put("secret", wc.getAppSecret());
        params.put("js_code", jsCode);
        params.put("grant_type", "authorization_code");
        String para = PaymentKit.packageSign(params, false);
        // 构造url
        String url = jsCode2sessionUrl + "?" + para;
        return new ApiResult(HttpUtils.get(url));
    }

    /**
     * 解密用户敏感数据
     *
     * @param sessionKey    会话密钥
     * @param encryptedData 明文
     * @param ivStr         加密算法的初始向量
     * @return {ApiResult}
     */
    public static ApiResult getUserInfo(String sessionKey, String encryptedData, String ivStr) {
        WxaBizDataCrypt dataCrypt = new WxaBizDataCrypt(sessionKey);
        String json = dataCrypt.decrypt(encryptedData, ivStr);
        return new ApiResult(json);
    }

    /**
     * 验证用户信息完整性
     *
     * @param sessionKey 会话密钥
     * @param rawData    微信用户基本信息
     * @param signature  数据签名
     * @return {boolean}
     */
    public static boolean checkUserInfo(String sessionKey, String rawData, String signature) {
        StringBuffer sb = new StringBuffer(rawData).append(sessionKey);
        String encryData = HashKit.sha1(sb.toString());
        return encryData.equals(signature);
    }

    private static String getPaidUnionIdUrl = "https://api.weixin.qq.com/wxa/getpaidunionid";

    /**
     * 用户支付完成后，获取该用户的 UnionId
     *
     * @param openId        支付用户唯一标识
     * @param transactionId 微信支付订单号
     * @param mchId         商户号
     * @param outTradeNo    商户订单号
     * @return {ApiResult}
     */
    public static ApiResult getPaidUnionId(String openId, String transactionId, String mchId, String outTradeNo) {
        ParaMap paraMap = ParaMap.create()
                .put("access_token", WxaAccessTokenApi.getAccessTokenStr())
                .put("openId", openId);

        if (StrKit.notBlank(transactionId)) {
            paraMap.put("transaction_id", transactionId);
        }
        if (StrKit.notBlank(mchId) && StrKit.notBlank(outTradeNo)) {
            paraMap.put("mch_id", mchId);
            paraMap.put("out_trade_no", outTradeNo);
        }
        String jsonResult = HttpUtils.get(getPaidUnionIdUrl, paraMap.getData());
        return new ApiResult(jsonResult);
    }
}
