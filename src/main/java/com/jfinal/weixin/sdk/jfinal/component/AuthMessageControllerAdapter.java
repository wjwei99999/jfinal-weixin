/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.jfinal.component;

import com.jfinal.log.Log;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.component.ComponentVerifyTicket;
import com.jfinal.weixin.sdk.cache.IAccessTokenCache;
import com.jfinal.weixin.sdk.msg.component.*;

/**
 * 接收微信服务器消息，自动解析成 InMsg 并分发到相应的处理方法<br>
 * 消息类型：<br>
 * component_verify_ticket 微信服务器每隔10分钟会向第三方的消息接收地址推送一次component_verify_ticket，用于获取第三方平台接口调用凭据<br>
 * authorized 授权成功通知<br>
 * unauthorized 取消授权通知<br>
 * updateauthorized 授权更新通知<br>
 */
public abstract class AuthMessageControllerAdapter extends AuthMessageController {

    private static final Log log = Log.getLog(AuthMessageController.class);

    protected static IAccessTokenCache accessTokenCache = ApiConfigKit.getAccessTokenCache();

    @Override
    protected void processMessage(ComponentVerifyTicketMsg msg) {
        log.info("收到ComponentVerifyTicket:" + msg);
        ComponentVerifyTicket verifyTicket = new ComponentVerifyTicket(msg.getAppId(),
                                                                       msg.getCreateTime(),
                                                                       msg.getComponentVerifyTicket());
        accessTokenCache.setComponentVerifyTicket(msg.getAppId(), verifyTicket);
    }

    @Override
    protected void processMessage(AuthorizedMsg msg) {

    }

    @Override
    protected void processMessage(UnAuthorizedMsg msg) {

    }

    @Override
    protected void processMessage(UpdateAuthorizedMsg msg) {

    }

    @Override
    protected void processMessage(NotDefinedComponentMsg msg) {

    }
}
