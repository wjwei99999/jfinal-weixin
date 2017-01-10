/**
 * Copyright (c) 2011-2014, L.cm 卢春梦 (qq596392912@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.wxaapp.msg.bean;

/**
 * 图片消息
 * @author L.cm
 *
 */
public class WxaImageMsg extends WxaMsg {
    private static final long serialVersionUID = 7044451698431281586L;

    private String picUrl;
    private String mediaId;
    private String msgId;
    
    public WxaImageMsg(String toUserName, String fromUserName, Integer createTime) {
        super(toUserName, fromUserName, createTime, "image");
    }

    public String getPicUrl() {
        return picUrl;
    }
    public String getMediaId() {
        return mediaId;
    }
    public String getMsgId() {
        return msgId;
    }
}
