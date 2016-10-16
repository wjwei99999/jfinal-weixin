/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api.component;

import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.cache.IAccessTokenCache;
import com.jfinal.weixin.sdk.kit.ParaMap;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.jfinal.weixin.sdk.utils.RetryUtils;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 认证并获取 access_token API
 * http://mp.weixin.qq.com/wiki/index.php?title=%E8%8E%B7%E5%8F%96access_token
 * <p>
 * AccessToken默认存储于内存中，可设置存储于redis或者实现IAccessTokenCache到数据库中实现分布式可用
 * <p>
 * 具体配置：
 * <pre>
 * ApiConfigKit.setAccessTokenCache(new RedisAccessTokenCache());
 * </pre>
 */
public class ComponentAuthorizerTokenApi {


    /**
     * https://api.weixin.qq.com/cgi-bin/component/api_component_token
     * {
     * "component_appid":"appid_value" ,
     * "component_appsecret": "appsecret_value",
     * "component_verify_ticket": "ticket_value"
     * }
     */
    private static String url = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";

    // 利用 appId 与 accessToken 建立关联，支持多账户
    static IAccessTokenCache componentAccessTokenCache = ApiConfigKit.getAccessTokenCache();

    /**
     * 从缓存中获取 access token，如果未取到或者 access token 不可用则先更新再获取
     *
     * @return AccessToken accessToken
     */
    public static ComponentAccessToken getComponentAccessToken() {
        String               appId  = ApiConfigKit.getComponentApiConfig().getAppId();
        ComponentAccessToken result = componentAccessTokenCache.get(appId);
        if (result != null && result.isAvailable())
            return result;

        refreshAccessToken();
        return componentAccessTokenCache.get(appId);
    }

    /**
     * 直接获取 accessToken 字符串，方便使用
     *
     * @return String accessToken
     */
    public static String getComponetAccessTokenStr() {
        return getComponentAccessToken().getComponetAccessToken();
    }

    /**
     * 强制更新 access token 值
     */
    public static synchronized void refreshAccessToken() {
        ApiConfig ac        = ApiConfigKit.getComponentApiConfig();
        String    appId     = ac.getAppId();
        String    appSecret = ac.getAppSecret();
        String    ticket    = componentAccessTokenCache.get(appId);
        final Map<String, String> queryParas = ParaMap.create("component_appid", appId).put(
            "component_appsecret",
            appSecret).put("component_verify_ticket", ticket).getData();

        // 最多三次请求
        ComponentAccessToken result = RetryUtils.retryOnException(3,
                                                                  new Callable<ComponentAccessToken>() {

                                                                      @Override
                                                                      public ComponentAccessToken call() throws Exception {
                                                                          String json = HttpUtils.get(
                                                                              url,
                                                                              queryParas);
                                                                          return new ComponentAccessToken(
                                                                              json);
                                                                      }
                                                                  });

        // 三次请求如果仍然返回了不可用的 access token 仍然 put 进去，便于上层通过 AccessToken 中的属性判断底层的情况
        componentAccessTokenCache.set(ac.getAppId(), result);
    }

}
