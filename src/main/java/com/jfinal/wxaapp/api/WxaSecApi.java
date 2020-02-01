
package com.jfinal.wxaapp.api;

import com.jfinal.kit.Kv;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.kit.ParaMap;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.jfinal.weixin.sdk.utils.JsonUtils;

import java.io.File;
import java.util.Map;

/**
 * @author Javen
 * 内容安全
 */
public class WxaSecApi {

    /**
     * 校验一张图片是否含有违法违规内容
     *
     * @param file 需要校验的图片文件
     * @return {ApiResult}
     */
    public static ApiResult imgSecCheck(File file) {
        String accessToken = WxaAccessTokenApi.getAccessTokenStr();
        String upload = HttpUtils.upload(SEC_TYPE.IMG_SEC_CHECK.get() + accessToken, file, null);
        return new ApiResult(upload);
    }

    /**
     * 校验一张图片是否含有违法违规内容
     *
     * @param mediaUrl  要检测的多媒体url
     * @param mediaType 1:音频 2:图片
     * @return {ApiResult}
     */
    public static ApiResult mediaCheckAsync(String mediaUrl, String mediaType) {
        String accessToken = WxaAccessTokenApi.getAccessTokenStr();
        Kv kv = Kv.by("media_url", mediaUrl)
                .set("media_type", mediaType);
        String jsonResult = HttpUtils.post(SEC_TYPE.MEDIA_CHECK_ASYNC.get() + accessToken, JsonUtils.toJson(kv));
        return new ApiResult(jsonResult);
    }
    /**
     * 校验一张图片是否含有违法违规内容
     *
     * @param content  要检测的文本内容，长度不超过 500KB
     * @return {ApiResult}
     */
    public static ApiResult msgSecCheck(String content) {
        String accessToken = WxaAccessTokenApi.getAccessTokenStr();
        Kv kv = Kv.by("content", content);
        String jsonResult = HttpUtils.post(SEC_TYPE.MSG_SEC_CHECK.get() + accessToken, JsonUtils.toJson(kv));
        return new ApiResult(jsonResult);
    }

    public enum SEC_TYPE {
        /**
         * 校验一张图片是否含有违法违规内容
         */
        IMG_SEC_CHECK("https://api.weixin.qq.com/wxa/img_sec_check?access_token="),
        /**
         * 异步校验图片/音频是否含有违法违规内容
         */
        MEDIA_CHECK_ASYNC("https://api.weixin.qq.com/wxa/media_check_async?access_token="),
        /**
         * 检查一段文本是否含有违法违规内容
         */
        MSG_SEC_CHECK("https://api.weixin.qq.com/wxa/msg_sec_check?access_token=");
        /**
         * 接口连接
         */
        private final String Url;

        SEC_TYPE(String Url) {
            this.Url = Url;
        }

        /**
         * 获取接口连接
         */
        public String get() {
            return Url;
        }
    }
}


