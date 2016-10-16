package com.jfinal.weixin.sdk.cache;

import com.jfinal.weixin.sdk.api.component.ComponentAccessToken;
import com.jfinal.weixin.sdk.api.component.ComponentAuthorizerAccessToken;
import com.jfinal.weixin.sdk.api.component.ComponentPreAuthCode;
import com.jfinal.weixin.sdk.api.component.ComponentVerifyTicket;

public interface IAccessTokenCache {

    // 默认超时时间7200秒 5秒用于程序执行误差
    int    TIME_OUT_OFFSET                          = 5;
    int    DEFAULT_TIME_OUT                         = 7200 - TIME_OUT_OFFSET;
    String ACCESS_TOKEN_PREFIX                      = "ac_";
    String COMPONENT_VERIFY_TICKET_PREFIX           = "cvt_";
    String COMPONENT_ACCESS_TOKEN_PREFIX            = "cat_";
    String COMPONENT_PRE_AUTH_CODE_PREFIX           = "cpac_";
    String COMPONENT_AUTHORIZER_ACCESS_TOKEN_PREFIX = "caat_";

    <T> T get(String key);

    void set(String key, Object value);

    void setComponentVerifyTicket(String key, ComponentVerifyTicket value);

    ComponentVerifyTicket getComponentVerifyTicket(String key);

    void setComponentAccessToken(String key, ComponentAccessToken value);

    ComponentAccessToken getComponentAccessToken(String key);

    void setComponentPreAuthCode(String key, ComponentPreAuthCode value);

    ComponentPreAuthCode getComponentPreAuthCode(String key);


    void setComponentAuthorizerAccessToken(String key1,
                                           String key2,
                                           ComponentAuthorizerAccessToken value);


    ComponentAuthorizerAccessToken getComponentAuthorizerAccessToken(String key1, String key2);

    void remove(String key);

}
