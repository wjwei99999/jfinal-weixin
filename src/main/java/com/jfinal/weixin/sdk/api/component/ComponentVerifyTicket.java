/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api.component;

import java.io.Serializable;

/**
 * <pre>
 * 接收文本消息
 * <xml>
 * <AppId> </AppId>
 * <CreateTime>1413192605 </CreateTime>
 * <InfoType> </InfoType>
 * <ComponentVerifyTicket> </ComponentVerifyTicket>
 * </xml>
 * </pre>
 */
public class ComponentVerifyTicket implements Serializable {
    private static final long serialVersionUID = 6458326639149049855L;
    /**
     * AppId	第三方平台appid
     * CreateTime	时间戳
     * InfoType	component_verify_ticket
     * ComponentVerifyTicket	Ticket内容
     */
    // 开发者微信号
    private String  appId;
    // 消息创建时间 （整型）
    private Integer createTime;
    private String  componentVerifyTicket;

    public ComponentVerifyTicket(String appId,
                                 Integer createTime,
                                 String componentVerifyTicket) {
        this.appId = appId;
        this.createTime = createTime;
        this.componentVerifyTicket = componentVerifyTicket;
    }

    public String getAppId() {
        return appId;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public String getComponentVerifyTicket() {
        return componentVerifyTicket;
    }
}




