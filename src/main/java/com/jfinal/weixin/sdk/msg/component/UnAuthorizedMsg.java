/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.msg.component;

/**
 * <pre>
 * 接收公众号对第三方平台取消授权的通知
 * <xml>
 * <AppId>第三方平台appid</AppId>
 * <CreateTime>1413192760</CreateTime>
 * <InfoType>unauthorized</InfoType>
 * <AuthorizerAppid>公众号appid</AuthorizerAppid>
 * </xml>
 * </pre>
 */
public class UnAuthorizedMsg extends AuthMsg {
    /**
     * AppId	第三方平台appid
     * CreateTime	时间戳
     * InfoType	component_verify_ticket
     * ComponentVerifyTicket	Ticket内容
     */

    private String authorizerAppId;


    public UnAuthorizedMsg(String appId,
                           Integer createTime,
                           String infoType,
                           String authorizerAppId) {
        super(appId, createTime, infoType);
        this.authorizerAppId = authorizerAppId;
    }

    public String getAuthorizerAppId() {
        return authorizerAppId;
    }

    @Override
    public String toString() {
        return "UnAuthorizedMsg{" +
               "authorizerAppId='" + authorizerAppId + '\'' +
               "} " + super.toString();
    }
}




