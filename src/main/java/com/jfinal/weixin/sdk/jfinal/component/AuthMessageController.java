/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.jfinal.component;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.NotAction;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.kit.MsgEncryptKit;
import com.jfinal.weixin.sdk.msg.ComponentMsgParser;
import com.jfinal.weixin.sdk.msg.component.AuthMsg;
import com.jfinal.weixin.sdk.msg.component.AuthorizedMsg;
import com.jfinal.weixin.sdk.msg.component.ComponentVerifyTicketMsg;
import com.jfinal.weixin.sdk.msg.component.NotDefinedComponentMsg;
import com.jfinal.weixin.sdk.msg.component.UnAuthorizedMsg;
import com.jfinal.weixin.sdk.msg.component.UpdateAuthorizedMsg;

/**
 * 接收微信服务器消息，自动解析成 InMsg 并分发到相应的处理方法
 */
public abstract class AuthMessageController extends Controller {

    private              String  inMsgXml = null;        // 本次请求 xml数据
    private              AuthMsg inMsg    = null;            // 本次请求 xml 解析后的 InMsg 对象

    public abstract ApiConfig getComponentApiConfig();

    /**
     * weixin 公众号服务器调用唯一入口，即在开发者中心输入的 URL 必须要指向此 action
     */
    @Before(AuthMessageInterceptor.class)
    public final void index() {
        // 开发模式输出微信服务发送过来的  xml 消息
        if (ApiConfigKit.isDevMode()) {
            System.out.println("接收消息:");
            System.out.println(getInMsgXml());
        }

        // 解析消息并根据消息类型分发到相应的处理方法
        AuthMsg msg = getAuthMsg();
        if (msg instanceof ComponentVerifyTicketMsg) {
            processMessage((ComponentVerifyTicketMsg) msg);
        } else if (msg instanceof AuthorizedMsg) {
            processMessage((AuthorizedMsg) msg);
        } else if (msg instanceof UnAuthorizedMsg) {
            processMessage((UnAuthorizedMsg) msg);
        } else if (msg instanceof UpdateAuthorizedMsg) {
            processMessage((UpdateAuthorizedMsg) msg);
        } else {
            processMessage((NotDefinedComponentMsg) msg);
        }
        renderText("success");
        return;
    }


    abstract void processMessage(ComponentVerifyTicketMsg msg);

    abstract void processMessage(AuthorizedMsg msg);

    abstract void processMessage(UnAuthorizedMsg msg);

    abstract void processMessage(UpdateAuthorizedMsg msg);

    abstract void processMessage(NotDefinedComponentMsg msg);


    @Before(NotAction.class)
    public String getInMsgXml() {
        if (inMsgXml == null) {
            inMsgXml = HttpKit.readData(getRequest());
            inMsgXml = MsgEncryptKit.decryptComponent(inMsgXml,
                                                      getPara("timestamp"),
                                                      getPara("nonce"),
                                                      getPara("msg_signature"));

        }
        if (StrKit.isBlank(inMsgXml)) {
            throw new RuntimeException(
                "请不要在浏览器中请求该连接,调试请查看WIKI:http://git.oschina.net/jfinal/jfinal-weixin/wikis/JFinal-weixin-demo%E5%92%8C%E8%B0%83%E8%AF%95");
        }
        return inMsgXml;
    }

    @Before(NotAction.class)
    public AuthMsg getAuthMsg() {
        if (inMsg == null)
            inMsg = ComponentMsgParser.parse(getInMsgXml());
        return inMsg;
    }

}
