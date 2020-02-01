
package com.jfinal.wxaapp.api;

import com.jfinal.kit.Kv;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.jfinal.weixin.sdk.utils.JsonUtils;

/**
 * @author Javen
 * SOTER 生物认证秘钥签名验证
 */
public class WxaVerifySignatureApi {
    private static String verifySignatureUrl = "https://api.weixin.qq.com/cgi-bin/soter/verify_signature?access_token=";

    /**
     * 生物认证秘钥签名验证
     *
     * @param openId        用户 openid
     * @param jsonString    通过 wx.startSoterAuthentication 成功回调获得的 resultJSON 字段
     * @param jsonSignature 通过 wx.startSoterAuthentication 成功回调获得的 resultJSONSignature 字段
     * @return {ApiResult}
     */
    public static ApiResult verifySignature(String openId, String jsonString, String jsonSignature) {
        String accessToken = WxaAccessTokenApi.getAccessTokenStr();
        Kv kv = Kv.by("openid", openId)
                .set("json_string", jsonString)
                .set("json_signature", jsonSignature);
        String jsonResult = HttpUtils.post(verifySignatureUrl + accessToken, JsonUtils.toJson(kv));
        return new ApiResult(jsonResult);
    }
}
