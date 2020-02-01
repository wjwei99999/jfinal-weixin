/**
 * @author Javen
 */
package com.jfinal.wxaapp.api;

import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.wxaapp.WxaConfig;
import com.jfinal.wxaapp.WxaConfigKit;

public class WxaSubscribeMsgApiTest {
    public static void main(String[] args) {
        WxaConfig wc = new WxaConfig();
        wc.setAppId("wxf30d9b9b316d5de4");
        wc.setAppSecret("bf0f1a06ba7cc16be643a250ca40213b");

        WxaConfigKit.setWxaConfig(wc);

        ApiResult category = WxaSubscribeMsgApi.getCategory();
        System.out.println("category:");
        System.out.println(category);

        ApiResult templateTitles = WxaSubscribeMsgApi.getPubTemplateTitles("616,612,298", null, null);
        System.out.println("templateTitles:");
        System.out.println(templateTitles);

        ApiResult template = WxaSubscribeMsgApi.getTemplate();
        System.out.println("template:");
        System.out.println(template);

        ApiResult templateKeyWords = WxaSubscribeMsgApi.getPubTemplateKeyWords("99");
        System.out.println("templateKeyWords:");
        System.out.println(templateKeyWords);


        WxaSubscribeMsgApi wxaSubscribeMsgApi = new WxaSubscribeMsgApi();
        WxaSubscribeMsgApi.SubTemplateItem subTemplateItem = wxaSubscribeMsgApi.new SubTemplateItem();
        subTemplateItem.put("phrase3", wxaSubscribeMsgApi.new Item("允许参与"));
        subTemplateItem.put("thing4", wxaSubscribeMsgApi.new Item("IJPay 线下聚会"));
        subTemplateItem.put("date5", wxaSubscribeMsgApi.new Item("2020-02-02 14:30"));
        subTemplateItem.put("thing6", wxaSubscribeMsgApi.new Item("深圳"));

        ApiResult send = WxaSubscribeMsgApi.send(
                "oUikW0Tmx9FYrSDc7SGMYqWJMClo",
                "vXVOFkL6n63UMIEM5aCa1gMCbnwMMYUO06S6IGf7J8c",
                subTemplateItem,
                "IJPay?author=Javen"
        );
        System.out.println("send:");
        System.out.println(send);
    }
}
