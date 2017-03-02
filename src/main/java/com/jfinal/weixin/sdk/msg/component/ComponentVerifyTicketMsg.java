/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.msg.component;

/**
 * <pre>
 * 接收微信服务器每隔 10 分钟定时推送 component_verify_ticket 的消息
 * <xml>
 * <AppId>第三方平台appid</AppId>
 * <CreateTime>1413192605 </CreateTime>
 * <InfoType>component_verify_ticket</InfoType>
 * <ComponentVerifyTicket>Ticket内容</ComponentVerifyTicket>
 * </xml>
 * </pre>
 */
public class ComponentVerifyTicketMsg extends AuthMsg {
    /**
     * AppId	第三方平台appid
     * CreateTime	时间戳
     * InfoType	component_verify_ticket
     * ComponentVerifyTicket	Ticket内容
     */

    private String componentVerifyTicket;

    public ComponentVerifyTicketMsg(String appId,
                                    Integer createTime,
                                    String infoType,
                                    String componentVerifyTicket) {
        super(appId, createTime, infoType);
        this.componentVerifyTicket = componentVerifyTicket;
    }

    public String getComponentVerifyTicket() {
        return componentVerifyTicket;
    }


    @Override
    public String toString() {
        return "ComponentVerifyTicketMsg{" +
               "componentVerifyTicket='" + componentVerifyTicket + '\'' +
               "} " + super.toString();
    }
}




