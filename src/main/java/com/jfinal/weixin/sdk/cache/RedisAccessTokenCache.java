package com.jfinal.weixin.sdk.cache;

import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import com.jfinal.weixin.sdk.api.component.ComponentAccessToken;
import com.jfinal.weixin.sdk.api.component.ComponentAuthorizerAccessToken;
import com.jfinal.weixin.sdk.api.component.ComponentPreAuthCode;
import com.jfinal.weixin.sdk.api.component.ComponentVerifyTicket;

public class RedisAccessTokenCache implements IAccessTokenCache {


    private final Cache cache;

    public RedisAccessTokenCache() {
        this.cache = Redis.use();
    }

    public RedisAccessTokenCache(String cacheName) {
        this.cache = Redis.use(cacheName);
    }

    public RedisAccessTokenCache(Cache cache) {
        this.cache = cache;
    }

    @Override
    public <T> T get(String key) {
        return cache.get(ACCESS_TOKEN_PREFIX + key);
    }

    @Override
    public void set(String key, Object object) {
        cache.setex(ACCESS_TOKEN_PREFIX + key, DEFAULT_TIME_OUT, object);
    }

    @Override
    public void setComponentVerifyTicket(String key, ComponentVerifyTicket value) {
        cache.set(COMPONENT_VERIFY_TICKET_PREFIX + key, value);
    }

    @Override
    public ComponentVerifyTicket getComponentVerifyTicket(String key) {
        return cache.get(COMPONENT_VERIFY_TICKET_PREFIX + key);
    }

    @Override
    public void setComponentAccessToken(String key, ComponentAccessToken value) {
        cache.setex(COMPONENT_ACCESS_TOKEN_PREFIX + key,
                    value.getExpiresIn() - TIME_OUT_OFFSET,
                    value);
    }

    @Override
    public ComponentAccessToken getComponentAccessToken(String key) {
        return cache.get(COMPONENT_ACCESS_TOKEN_PREFIX + key);
    }

    @Override
    public void setComponentPreAuthCode(String key, ComponentPreAuthCode value) {
        cache.setex(COMPONENT_PRE_AUTH_CODE_PREFIX + key,
                    value.getExpiresIn() - TIME_OUT_OFFSET,
                    value);
    }

    @Override
    public ComponentPreAuthCode getComponentPreAuthCode(String key) {
        return cache.get(COMPONENT_PRE_AUTH_CODE_PREFIX + key);
    }

    @Override
    public void setComponentAuthorizerAccessToken(String key1,
                                                  String key2,
                                                  ComponentAuthorizerAccessToken value) {
        cache.set(COMPONENT_AUTHORIZER_ACCESS_TOKEN_PREFIX + key1 + "_" + key2, value);
    }


    @Override
    public ComponentAuthorizerAccessToken getComponentAuthorizerAccessToken(String key1,
                                                                            String key2) {
        return cache.get(COMPONENT_AUTHORIZER_ACCESS_TOKEN_PREFIX + key1 + "_" + key2);
    }

    @Override
    public void remove(String key) {
        cache.del(ACCESS_TOKEN_PREFIX + key);
    }

}
