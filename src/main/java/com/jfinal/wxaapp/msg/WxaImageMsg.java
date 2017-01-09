/**
 * Copyright (c) 2011-2014, L.cm 卢春梦 (qq596392912@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.wxaapp.msg;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 图片消息
 * @author L.cm
 *
 */
public class WxaImageMsg extends WxaMsg {
    private static final long serialVersionUID = 7044451698431281586L;

    @JsonProperty("PicUrl")
    @JSONField(name = "PicUrl")
    private String picUrl;
    
    @JsonProperty("MediaId")
    @JSONField(name = "MediaId")
    private String mediaId;
    
    @JsonProperty("MsgId")
    @JSONField(name = "MsgId")
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
