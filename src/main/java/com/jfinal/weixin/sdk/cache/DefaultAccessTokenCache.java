package com.jfinal.weixin.sdk.cache;

import com.jfinal.weixin.sdk.api.component.ComponentAccessToken;
import com.jfinal.weixin.sdk.api.component.ComponentAuthorizerAccessToken;
import com.jfinal.weixin.sdk.api.component.ComponentPreAuthCode;
import com.jfinal.weixin.sdk.api.component.ComponentVerifyTicket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认存储与内存中
 */
public class DefaultAccessTokenCache implements IAccessTokenCache {

    private Map<String, Object> map = new ConcurrentHashMap<String, Object>();

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key) {
        return (T) map.get(ACCESS_TOKEN_PREFIX + key);
    }

    @Override
    public void set(String key, Object value) {
        map.put(ACCESS_TOKEN_PREFIX + key, value);
    }

    @Override
    public void setComponentVerifyTicket(String key, ComponentVerifyTicket value) {
        map.put(COMPONENT_VERIFY_TICKET_PREFIX + key, value);
    }

    @Override
    public ComponentVerifyTicket getComponentVerifyTicket(String key) {
        return (ComponentVerifyTicket) map.get(COMPONENT_VERIFY_TICKET_PREFIX + key);
    }

    @Override
    public void setComponentAccessToken(String key, ComponentAccessToken value) {
        map.put(COMPONENT_ACCESS_TOKEN_PREFIX + key, value);
    }

    @Override
    public ComponentAccessToken getComponentAccessToken(String key) {
        return (ComponentAccessToken) map.get(COMPONENT_ACCESS_TOKEN_PREFIX + key);
    }

    @Override
    public void setComponentPreAuthCode(String key, ComponentPreAuthCode value) {
        map.put(COMPONENT_PRE_AUTH_CODE_PREFIX + key, value);
    }

    @Override
    public ComponentPreAuthCode getComponentPreAuthCode(String key) {
        return (ComponentPreAuthCode) map.get(COMPONENT_PRE_AUTH_CODE_PREFIX + key);
    }

    @Override
    public void setComponentAuthorizerAccessToken(String key1,
                                                  String key2,
                                                  ComponentAuthorizerAccessToken value) {
        map.put(COMPONENT_AUTHORIZER_ACCESS_TOKEN_PREFIX + key1 + "_" + key2, value);

    }

    @Override
    public ComponentAuthorizerAccessToken getComponentAuthorizerAccessToken(String key1,
                                                                            String key2) {
        return (ComponentAuthorizerAccessToken) map.get(COMPONENT_AUTHORIZER_ACCESS_TOKEN_PREFIX + key1 + "_" + key2);
    }

    @Override
    public void remove(String key) {
        map.remove(ACCESS_TOKEN_PREFIX + key);
    }

}
