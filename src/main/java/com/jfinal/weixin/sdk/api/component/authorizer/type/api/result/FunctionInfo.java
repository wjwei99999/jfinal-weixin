package com.jfinal.weixin.sdk.api.component.authorizer.type.api.result;

import com.jfinal.weixin.sdk.api.component.authorizer.type.FunctionType;

import java.util.ArrayList;
import java.util.List;

public class FunctionInfo {
    private String              appid;
    private List<FunctionScope> func_info;
    private String              authorizer_appid;    // 正确获取到 access_token 时有值
    private String              authorizer_access_token;    // 正确获取到 access_token 时有值
    private String              authorizer_refresh_token;    // 正确获取到 access_token 时有值

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public List<FunctionScope> getFunc_info() {
        return func_info;
    }

    public void setFunc_info(List<FunctionScope> func_info) {
        this.func_info = func_info;
    }

    public List<FunctionType> to() {
        List<FunctionType> functionTypes = new ArrayList<FunctionType>();
        for (FunctionScope scope : func_info) {
            functionTypes.add(FunctionType.of(scope.getFuncscope_category().getId()));
        }
        return functionTypes;
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
}