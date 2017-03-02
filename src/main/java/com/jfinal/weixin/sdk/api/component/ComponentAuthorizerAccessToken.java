/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api.component;

import com.jfinal.weixin.sdk.api.ReturnCode;
import com.jfinal.weixin.sdk.utils.JsonUtils;
import com.jfinal.weixin.sdk.utils.RetryUtils.ResultCheck;

import java.io.Serializable;
import java.util.Map;

/**
 * 封装 access_token
 */
public class ComponentAuthorizerAccessToken implements ResultCheck, Serializable {

    private static final long serialVersionUID = -822464425433824314L;
    /**
     * "authorizer_appid": "wxf8b4f85f3a794e77",
     * "authorizer_access_token": "QXjUqNqfYVH0yBE1iI_7vuN_9gQbpjfK7hYwJ3P7xOa88a89-Aga5x1NMYJyB8G2yKt1KCl0nPC3W9GJzw0Zzq_dBxc8pxIGUNi_bFes0qM",
     * "expires_in": 7200,
     * "authorizer_refresh_token": "dTo-YCXPL4llX-u1W1pPpnp8Hgm4wpJtlR6iV0doKdY",
     */
    private String  authorizer_appid;    // 正确获取到 access_token 时有值
    private String  authorizer_access_token;    // 正确获取到 access_token 时有值
    private String  authorizer_refresh_token;    // 正确获取到 access_token 时有值
    private Integer expires_in;        // 正确获取到 access_token 时有值
    private Integer errcode;        // 出错时有值
    private String  errmsg;            // 出错时有值
    private Long    expiredTime;        // 正确获取到 access_token 时有值，存放过期时间
    private String  json;

    @SuppressWarnings("unchecked")
    public ComponentAuthorizerAccessToken(String jsonStr) {
        this.json = jsonStr;

        try {
            Map<String, Object> temp = JsonUtils.parse(jsonStr, Map.class);
            authorizer_appid = (String) temp.get("authorizer_appid");
            authorizer_refresh_token = (String) temp.get("authorizer_refresh_token");
            authorizer_access_token = (String) temp.get("authorizer_access_token");
            expires_in = getInt(temp, "expires_in");
            errcode = getInt(temp, "errcode");
            errmsg = (String) temp.get("errmsg");

            if (expires_in != null)
                expiredTime = System.currentTimeMillis() + ((expires_in - 5) * 1000);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ComponentAuthorizerAccessToken(String appId,
                                          String authorizerAccessToken,
                                          String authorizerRefreshToken) {
        this.authorizer_appid = appId;
        this.authorizer_access_token = authorizerAccessToken;
        this.authorizer_refresh_token = authorizerRefreshToken;
    }

    public String getJson() {
        return json;
    }

    public boolean isAvailable() {
        if (expiredTime == null)
            return false;
        if (errcode != null)
            return false;
        if (expiredTime < System.currentTimeMillis())
            return false;
        return authorizer_access_token != null;
    }

    private Integer getInt(Map<String, Object> temp, String key) {
        Number number = (Number) temp.get(key);
        return number == null ? null : number.intValue();
    }

    public String getAuthorizerAppId() {
        return authorizer_appid;
    }

    public String getAuthorizerAccessToken() {
        return authorizer_access_token;
    }

    public String getAuthorizerRefreshToken() {
        return authorizer_refresh_token;
    }

    public Integer getExpiresIn() {
        return expires_in;
    }

    public Long getExpiredTime() {
        return expiredTime;
    }

    public Integer getErrorCode() {
        return errcode;
    }

    public String getErrorMsg() {
        if (errcode != null) {
            String result = ReturnCode.get(errcode);
            if (result != null)
                return result;
        }
        return errmsg;
    }

    @Override
    public boolean matching() {
        return isAvailable();
    }
    
    /**
     * 组织存储刷新后的令牌。
     * 刷新令牌接口不返回授权方 AppId，所以要重新组织存储
     * @param tokenCache
     * @param refreshToken
     */
    public ComponentAuthorizerAccessToken (String authorizerAppId,ComponentAuthorizerAccessToken refreshToken){
    	
    	try {
            authorizer_appid = authorizerAppId;
            authorizer_refresh_token = refreshToken.getAuthorizerRefreshToken();
            authorizer_access_token = refreshToken.getAuthorizerAccessToken();
            expires_in = refreshToken.getExpiresIn();
            errcode = refreshToken.getErrorCode();
            errmsg = refreshToken.getErrorMsg();

            if (expires_in != null)
                expiredTime = System.currentTimeMillis() + ((expires_in - 5) * 1000);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
