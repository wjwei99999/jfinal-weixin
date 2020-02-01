
package com.jfinal.wxaapp.api;

import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.kit.ParaMap;
import com.jfinal.weixin.sdk.utils.HttpUtils;

import java.io.File;
import java.util.Map;

/**
 * @author Javen
 * OCR 识别
 */
public class WxaOcrApi {
    /**
     * OCR 识别
     *
     * @param type   OCR 识别类型
     * @param imgUrl 需要识别的图片链接
     * @return {ApiResult}
     */
    public static ApiResult ocrByUrl(OCR_TYPE type, String imgUrl) {
        String accessToken = WxaAccessTokenApi.getAccessTokenStr();
        Map<String, String> queryParas = ParaMap.create("img_url", imgUrl).getData();
        return new ApiResult(HttpUtils.get(type.get() + accessToken, queryParas));
    }

    /**
     * OCR 识别
     *
     * @param type OCR 识别类型
     * @param file 需要识别的图片文件
     * @return {ApiResult}
     */
    public static ApiResult ocrByFile(OCR_TYPE type, File file) {
        String accessToken = WxaAccessTokenApi.getAccessTokenStr();
        String upload = HttpUtils.upload(type.get() + accessToken, file, null);
        return new ApiResult(upload);
    }

    public  enum OCR_TYPE {
        /**
         * 银行卡 OCR 识别
         */
        BANK_CARD("https://api.weixin.qq.com/cv/ocr/bankcard?access_token="),
        /**
         * 营业执照 OCR 识别
         */
        BUSINESS_LICENSE("https://api.weixin.qq.com/cv/ocr/bizlicense?access_token="),
        /**
         * 驾驶证 OCR 识别
         */
        DRIVER_LICENSE("https://api.weixin.qq.com/cv/ocr/drivinglicense?access_token="),
        /**
         * 身份证 OCR 识别
         */
        ID_CARD("https://api.weixin.qq.com/cv/ocr/idcard?access_token="),
        /**
         * 通用印刷体 OCR 识别
         */
        PRINTED_TEXT("https://api.weixin.qq.com/cv/ocr/comm?access_token="),
        /**
         * 行驶证 OCR 识别
         */
        VEHICLE_LICENSE("https://api.weixin.qq.com/cv/ocr/driving?access_token=");
        /**
         * 接口连接
         */
        private final String Url;

        OCR_TYPE(String Url) {
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


