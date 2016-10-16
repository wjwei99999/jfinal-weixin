/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.msg.component;

/**
 * <pre>
 * 接收文本消息
 * <xml>
 * <AppId>第三方平台appid</AppId>
 * <CreateTime>1413192760</CreateTime>
 * <InfoType>updateauthorized</InfoType>
 * <AuthorizerAppid>公众号appid</AuthorizerAppid>
 * <AuthorizationCode>授权码（code）</AuthorizationCode>
 * <AuthorizationCodeExpiredTime>过期时间</AuthorizationCodeExpiredTime>
 * </xml>
 * </pre>
 */
public class UpdateAuthorizedMsg extends AuthMsg {
    /**
     * AppId	第三方平台appid
     * CreateTime	时间戳
     * InfoType	component_verify_ticket
     * ComponentVerifyTicket	Ticket内容
     */

    private String authorizerAppId;
    private String authorizationCode;
    private String authorizationCodeExpiredTime;

    public UpdateAuthorizedMsg(String appId,
                               Integer createTime,
                               String infoType,
                               String authorizerAppId,
                               String authorizationCode,
                               String authorizationCodeExpiredTime) {
        super(appId, createTime, infoType);
        this.authorizerAppId = authorizerAppId;
        this.authorizationCode = authorizationCode;
        this.authorizationCodeExpiredTime = authorizationCodeExpiredTime;
    }

    public String getAuthorizerAppId() {
        return authorizerAppId;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public String getAuthorizationCodeExpiredTime() {
        return authorizationCodeExpiredTime;
    }

    @Override
    public String toString() {
        return "AuthorizedMsg{" +
               "authorizerAppId='" + authorizerAppId + '\'' +
               ", authorizationCode='" + authorizationCode + '\'' +
               ", authorizationCodeExpiredTime='" + authorizationCodeExpiredTime + '\'' +
               "} " + super.toString();
    }
}




