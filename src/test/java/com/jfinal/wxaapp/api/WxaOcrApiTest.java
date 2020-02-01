
package com.jfinal.wxaapp.api;

import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.wxaapp.WxaConfig;
import com.jfinal.wxaapp.WxaConfigKit;

import java.io.File;

/**
 * @author Javen
 */
public class WxaOcrApiTest {
    public static void main(String[] args) {
        WxaConfig wc = new WxaConfig();
        wc.setAppId("wxf30d9b9b316d5de4");
        wc.setAppSecret("bf0f1a06ba7cc16be643a250ca40213b");

        WxaConfigKit.setWxaConfig(wc);

        ApiResult apiResult = WxaOcrApi.ocrByUrl(
                WxaOcrApi.OCR_TYPE.ID_CARD,
                "https://up.enterdesk.com/edpic_360_360/28/bc/80/28bc80d62c84ea7797197a6d7cb03394.jpg"
        );
        ApiResult apiResult2 = WxaOcrApi.ocrByFile(
                WxaOcrApi.OCR_TYPE.PRINTED_TEXT,
                new File("/Users/Javen/Documents/IJPay-Logo.png")
        );
        System.out.println(apiResult);
        System.out.println(apiResult2);
    }
}
