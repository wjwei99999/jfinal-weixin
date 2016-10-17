/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.msg;

import com.jfinal.kit.LogKit;
import com.jfinal.weixin.sdk.msg.component.*;
import com.jfinal.weixin.sdk.utils.XmlHelper;

public class ComponentMsgParser {
    private ComponentMsgParser() {
    }

    /**
     * 从 xml 中解析出各类消息与事件
     *
     * @param xml xml字符串
     * @return {InMsg}
     */
    public static AuthMsg parse(String xml) {
        XmlHelper xmlHelper = XmlHelper.of(xml);
        return doParse(xmlHelper);
    }

    /**
     * 消息类型
     * 1：text 文本消息
     * 2：image 图片消息
     * 3：voice 语音消息
     * 4：video 视频消息
     * shortvideo 小视频消息
     * 5：location 地址位置消息
     * 6：link 链接消息
     * 7：event 事件
     */
    private static AuthMsg doParse(XmlHelper xmlHelper) {

        String  infoType   = xmlHelper.getString("//InfoType");
        String  appId      = xmlHelper.getString("//AppId");
        Integer createTime = xmlHelper.getNumber("//CreateTime").intValue();
        if ("component_verify_ticket".equals(infoType))
            return parseComponentVerifyTicketMsg(xmlHelper, appId, createTime, infoType);
        if ("unauthorized".equals(infoType))
            return parseUnAuthorizedMsg(xmlHelper, appId, createTime, infoType);
        if ("authorized".equals(infoType))
            return parseAuthorizedMsg(xmlHelper, appId, createTime, infoType);
        if ("updateauthorized".equals(infoType))
            return parseUpdateAuthorizedMsg(xmlHelper, appId, createTime, infoType);

        LogKit.error("无法识别的消息类型 " + infoType + "，请查阅微信公众平台开发文档");
        return parseNotDefinedComponentMsg(appId, createTime, infoType);
    }

    private static AuthMsg parseNotDefinedComponentMsg(String appId,
                                                       Integer createTime,
                                                       String msgType) {
        NotDefinedComponentMsg msg = new NotDefinedComponentMsg(appId, createTime, msgType);
        return msg;
    }

    private static AuthMsg parseComponentVerifyTicketMsg(XmlHelper xmlHelper,
                                                         String appId,
                                                         Integer createTime,
                                                         String infoType) {
        final String ticket = xmlHelper.getString("//ComponentVerifyTicket");
        ComponentVerifyTicketMsg msg = new ComponentVerifyTicketMsg(appId,
                                                                    createTime,
                                                                    infoType,
                                                                    ticket);
        return msg;
    }

    /**
     * <xml>
     * <AppId>第三方平台appid</AppId>
     * <CreateTime>1413192760</CreateTime>
     * <InfoType>unauthorized</InfoType>
     * <AuthorizerAppid>公众号appid</AuthorizerAppid>
     * </xml>
     */

    private static AuthMsg parseUnAuthorizedMsg(XmlHelper xmlHelper,
                                                String appId,
                                                Integer createTime,
                                                String infoType) {
        final String authorizerAppId = xmlHelper.getString("//AuthorizerAppid");
        UnAuthorizedMsg msg = new UnAuthorizedMsg(appId,
                                                  createTime,
                                                  infoType,
                                                  authorizerAppId);
        return msg;
    }

    /**
     * <xml>
     * <AppId>第三方平台appid</AppId>
     * <CreateTime>1413192760</CreateTime>
     * <InfoType>authorized</InfoType>
     * <AuthorizerAppid>公众号appid</AuthorizerAppid>
     * <AuthorizationCode>授权码（code）</AuthorizationCode>
     * <AuthorizationCodeExpiredTime>过期时间</AuthorizationCodeExpiredTime>
     * </xml>
     */
    private static AuthMsg parseAuthorizedMsg(XmlHelper xmlHelper,
                                              String appId,
                                              Integer createTime,
                                              String infoType) {
        final String authorizerAppId   = xmlHelper.getString("//AuthorizerAppid");
        final String authorizationCode = xmlHelper.getString("//AuthorizationCode");
        final String authorizationCodeExpiredTime = xmlHelper.getString(
            "//AuthorizationCodeExpiredTime");
        AuthorizedMsg msg = new AuthorizedMsg(appId,
                                              createTime,
                                              infoType,
                                              authorizerAppId,
                                              authorizationCode,
                                              authorizationCodeExpiredTime);
        return msg;
    }

    private static AuthMsg parseUpdateAuthorizedMsg(XmlHelper xmlHelper,
                                                    String appId,
                                                    Integer createTime,
                                                    String infoType) {
        final String authorizerAppId   = xmlHelper.getString("//AuthorizerAppid");
        final String authorizationCode = xmlHelper.getString("//AuthorizationCode");
        final String authorizationCodeExpiredTime = xmlHelper.getString(
            "//AuthorizationCodeExpiredTime");
        UpdateAuthorizedMsg msg = new UpdateAuthorizedMsg(appId,
                                                          createTime,
                                                          infoType,
                                                          authorizerAppId,
                                                          authorizationCode,
                                                          authorizationCodeExpiredTime);
        return msg;
    }
}
