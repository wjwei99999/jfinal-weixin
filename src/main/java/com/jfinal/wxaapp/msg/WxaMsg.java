/**
 * Copyright (c) 2011-2014, L.cm 卢春梦 (qq596392912@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.wxaapp.msg;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
public class WxaMsg implements Serializable {
    // 开发者微信号
    @JsonProperty("ToUserName")
    @JSONField(name = "ToUserName")
    protected String toUserName;
    // 发送方帐号（一个OpenID）
    @JsonProperty("FromUserName")
    @JSONField(name = "FromUserName")
    protected String fromUserName;
    // 消息创建时间 （整型）
    @JsonProperty("CreateTime")
    @JSONField(name = "CreateTime")
    protected Integer createTime;
    /**
     * 消息类型
     * 1：text 文本消息
     * 2：image 图片消息
     * 3: Event 事件消息
     */
    @JsonProperty("MsgType")
    protected String msgType;
    
    public WxaMsg(String toUserName, String fromUserName, Integer createTime, String msgType) {
        this.toUserName = toUserName;
        this.fromUserName = fromUserName;
        this.createTime = createTime;
        this.msgType = msgType;
    }

    public String getToUserName() {
        return toUserName;
    }
    public String getFromUserName() {
        return fromUserName;
    }
    public Integer getCreateTime() {
        return createTime;
    }
    public String getMsgType() {
        return msgType;
    }
}
