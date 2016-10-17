/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api.component.authorizer;

import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.api.component.authorizer.type.FunctionType;
import com.jfinal.weixin.sdk.api.component.authorizer.type.api.result.FunctionInfo;
import com.jfinal.weixin.sdk.utils.JsonUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 封装 access_token
 */
public class AuthorizationInfo implements Serializable {

    private static final long serialVersionUID = -1974768736356874602L;

    private String             appid;
    private List<FunctionType> authorizedFunctions;
    private String             authorizer_appid;    // 正确获取到 access_token 时有值
    private String             authorizer_access_token;    // 正确获取到 access_token 时有值
    private String             authorizer_refresh_token;    // 正确获取到 access_token 时有值
    private String             json;


    public AuthorizationInfo(String jsonStr) {
        this.json = jsonStr;

        try {
            FunctionInfo temp = JsonUtils.parse(jsonStr, FunctionInfo.class);
            this.authorizedFunctions = temp.to();
            final String appid = temp.getAppid();
            if (StrKit.isBlank(appid)) {
                this.appid = temp.getAuthorizer_appid();
                this.authorizer_appid = temp.getAuthorizer_appid();
            } else {
                this.authorizer_appid = appid;
                this.appid = appid;
            }
            this.authorizer_access_token = temp.getAuthorizer_access_token();
            this.authorizer_refresh_token = temp.getAuthorizer_refresh_token();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public AuthorizationInfo(String appid, List<FunctionType> functionTypes) {
        this.appid = appid;
        this.authorizedFunctions = functionTypes;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public List<FunctionType> getAuthorizedFunctions() {
        return authorizedFunctions;
    }

    public void setAuthorizedFunctions(List<FunctionType> authorizedFunctions) {
        this.authorizedFunctions = authorizedFunctions;
    }

    public String getAuthorizer_appid() {
        return authorizer_appid;
    }

    public void setAuthorizer_appid(String authorizer_appid) {
        this.authorizer_appid = authorizer_appid;
    }

    public String getAuthorizer_access_token() {
        return authorizer_access_token;
    }

    public void setAuthorizer_access_token(String authorizer_access_token) {
        this.authorizer_access_token = authorizer_access_token;
    }

    public String getAuthorizer_refresh_token() {
        return authorizer_refresh_token;
    }

    public void setAuthorizer_refresh_token(String authorizer_refresh_token) {
        this.authorizer_refresh_token = authorizer_refresh_token;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
