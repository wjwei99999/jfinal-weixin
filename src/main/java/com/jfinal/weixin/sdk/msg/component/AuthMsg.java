/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.msg.component;

/**
 * <pre>
 * 接收公众号授权给第三方平台的消息
 * </pre>
 */
public abstract class AuthMsg {

    // 开发者微信号
    protected String appId;

    // 消息创建时间 （整型）
    protected Integer createTime;

    /**
     * 消息类型
     * 1：component_verify_ticket 每10分钟推送一次的安全ticket
     * 2：authorized 授权成功通知
     * 3：unauthorized 取消授权通知
     * 4：updateauthorized 授权更新通知
     */
    protected String infoType;

    public AuthMsg(String appId, Integer createTime, String infoType) {
        this.appId = appId;
        this.createTime = createTime;
        this.infoType = infoType;
    }

    public String getAppId() {
        return appId;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public String getInfoType() {
        return infoType;
    }

    @Override
    public String toString() {
        return "AuthMsg{" +
               "appId='" + appId + '\'' +
               ", createTime=" + createTime +
               ", infoType='" + infoType + '\'' +
               '}';
    }
}







