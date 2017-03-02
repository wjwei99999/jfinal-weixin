/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api.component;

import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.component.authorizer.AuthorizedInfo;
import com.jfinal.weixin.sdk.cache.IAccessTokenCache;
import com.jfinal.weixin.sdk.kit.ParaMap;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.jfinal.weixin.sdk.utils.JsonUtils;
import com.jfinal.weixin.sdk.utils.RetryUtils;

import java.text.MessageFormat;
import java.util.Map;
import java.util.ServiceLoader;
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
public class ComponentAuthApi {


    // 利用 appId 与 accessToken 建立关联，支持多账户

    /**
     * https://api.weixin.qq.com/cgi-bin/component/api_component_token
     * {
     * "component_appid":"appid_value" ,
     * "component_appsecret": "appsecret_value",
     * "component_verify_ticket": "ticket_value"
     * }
     */

    public static final String                       redirect      = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid={0}&pre_auth_code={1}&redirect_uri={2}";
    static              String                       auth          = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token={0}";
    static              String                       refresh       = "https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token?component_access_token={0}";
    static              String                       info          = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info?component_access_token={0}";
    static              IAccessTokenCache            tokenCache    = ApiConfigKit.getAccessTokenCache();
    static              ServiceLoader<AuthorizedApi> apiLoader     = ServiceLoader.load(AuthorizedApi.class);
    static              AuthorizedApi                authorizedApi = apiLoader.iterator().next();

    /**
     * 生成Authorize链接
     *
     * @param appId        应用id
     * @param redirect_uri 回跳地址
     * @return url
     */
    public static String getAuthorizeURL(String appId, String redirect_uri) {
        String pre_auth_code = ComponentPreAuthCodeApi.getComponentPreAuthCodeStr();
        String redirectURL   = MessageFormat.format(redirect, appId, pre_auth_code, redirect_uri);
        return redirectURL;
    }


    public static synchronized ApiResult auth(final String authorization_code) {
        ApiConfig ac    = ApiConfigKit.getComponentApiConfig();
        String    appId = ac.getAppId();
        final Map<String, String> queryParas = ParaMap.create("component_appid", appId)
                                                      .put("authorization_code", authorization_code)
                                                      .getData();

        final String paramUrl = MessageFormat.format(auth,
                                                     ComponentAccessTokenApi.getComponentAccessTokenStr());
        String          json      = HttpUtils.post(paramUrl, JsonUtils.toJson(queryParas));
        final ApiResult apiResult = new ApiResult(json);
        if (apiResult != null && apiResult.isSucceed()) {
            final String authorization_info = JsonUtils.toJson(apiResult.get(
                "authorization_info"));
            ComponentAuthorizerAccessToken token = new ComponentAuthorizerAccessToken(
                authorization_info);
            if (token.isAvailable()) {
                tokenCache.setComponentAuthorizerAccessToken(appId,
                                                             token.getAuthorizerAppId(),
                                                             token);
            }
        }
        return apiResult;
    }

    public static AuthorizedInfo getAuthorizer(String authorizer_appid) {
        ApiConfig ac    = ApiConfigKit.getComponentApiConfig();
        String    appId = ac.getAppId();
        final Map<String, String> queryParas = ParaMap.create("component_appid", appId)
                                                      .put("authorizer_appid", authorizer_appid)
                                                      .getData();

        final String paramUrl = MessageFormat.format(info,
                                                     ComponentAccessTokenApi.getComponentAccessTokenStr());
        String json = HttpUtils.post(paramUrl, JsonUtils.toJson(queryParas));
        return new AuthorizedInfo(json);
    }

    /**
     * 直接获取 accessToken 字符串，方便使用
     *
     * @return String accessToken
     */
    public static String getComponentAuthorizerAccessTokenStr() {
    	String authorizerAppId =ApiConfigKit.getThreadLocalAuthorizerAppId();
        return getComponentAuthorizerAccessToken(authorizerAppId).getAuthorizerAccessToken();
    }
    
    /**
     * 直接获取 accessToken 字符串，方便使用
     *
     * @return String accessToken
     */
    public static String getComponentAuthorizerAccessTokenStr(String authorizer_appid) {
        return getComponentAuthorizerAccessToken(authorizer_appid).getAuthorizerAccessToken();
    }
    
    /**
     * 从缓存中获取 access token，如果未取到或者 access token 不可用则先更新再获取
     * @param authorizer_appid 授权方 appid
     * @return ComponentAuthorizerAccessToken 授权方 accessToken
     */
    public static ComponentAuthorizerAccessToken getComponentAuthorizerAccessToken(String authorizer_appid) {
        String appId = ApiConfigKit.getComponentApiConfig().getAppId();
        ComponentAuthorizerAccessToken result = tokenCache.getComponentAuthorizerAccessToken(appId,
                                                                                             authorizer_appid);
        if (result != null && result.isAvailable()){
        	return result;
        }
        refreshAccessToken(authorizer_appid);
        return tokenCache.getComponentAuthorizerAccessToken(appId, authorizer_appid);
    }
    
    /**
     * 强制更新 access token 值
     */
    public static synchronized void refreshAccessToken() {
    	String authorizerAppId =ApiConfigKit.getThreadLocalAuthorizerAppId();
    	refreshAccessToken(authorizerAppId);
    }
    
    /**
     * 强制更新 access token 值
     */
    public static synchronized void refreshAccessToken(String authorizer_appid) {
        ApiConfig ac        = ApiConfigKit.getComponentApiConfig();
        String    appId     = ac.getAppId();
        ComponentAuthorizerAccessToken accessToken = tokenCache.getComponentAuthorizerAccessToken(
            appId,
            authorizer_appid);
        if (accessToken == null) {
            throw new NullPointerException("授权方令牌 ComponentAuthorizerAccessToken 为 null");
        	//accessToken = authorizedApi.of(authorizer_appid);
        }
        final Map<String, String> queryParas = ParaMap.create("component_appid", appId)
                                                      .put("authorizer_appid",
                                                           accessToken.getAuthorizerAppId())
                                                      .put("authorizer_refresh_token",
                                                           accessToken.getAuthorizerRefreshToken()).getData();
        // 最多三次请求
        ComponentAuthorizerAccessToken result = RetryUtils.retryOnException(3,
                                                                            new Callable<ComponentAuthorizerAccessToken>() {

                                                                                @Override
                                                                                public ComponentAuthorizerAccessToken call() throws Exception {
                                                                                    final String paramUrl = MessageFormat.format(
                                                                                        refresh,
                                                                                        ComponentAccessTokenApi.getComponentAccessTokenStr());
                                                                                    String json = HttpUtils.post(
                                                                                        paramUrl,
                                                                                        JsonUtils.toJson(
                                                                                            queryParas));
                                                                                    return new ComponentAuthorizerAccessToken(
                                                                                        json);
                                                                                }
                                                                            });

        // 三次请求如果仍然返回了不可用的 access token 仍然 put 进去，便于上层通过 AccessToken 中的属性判断底层的情况
        tokenCache.setComponentAuthorizerAccessToken(appId, authorizer_appid, new ComponentAuthorizerAccessToken(accessToken.getAuthorizerAppId(),result));
    }

}
