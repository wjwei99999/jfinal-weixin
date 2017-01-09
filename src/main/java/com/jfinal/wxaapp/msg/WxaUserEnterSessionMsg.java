/**
 * Copyright (c) 2011-2014, L.cm 卢春梦 (qq596392912@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.wxaapp.msg;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WxaUserEnterSessionMsg extends WxaMsg {
    private static final long serialVersionUID = 1909321793183745030L;

    @JsonProperty("Event")
    @JSONField(name = "Event")
    protected String event;
    
    @JsonProperty("SessionFrom")
    @JSONField(name = "SessionFrom")
    protected String sessionFrom;
    
    public WxaUserEnterSessionMsg(String toUserName, String fromUserName, Integer createTime) {
        super(toUserName, fromUserName, createTime, "event");
    }

    public String getEvent() {
        return event;
    }
    public String getSessionFrom() {
        return sessionFrom;
    }
    
}
