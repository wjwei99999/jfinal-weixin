/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.jfinal.component;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.kit.SignatureCheckKit;

/**
 * Msg 拦截器
 * 1：通过 MsgController.getComponentApiConfig() 得到 ApiConfig 对象，并将其绑定到当前线程之上(利用了 ApiConfigKit 中的 ThreadLocal 对象)
 * 2：响应开发者中心服务器配置 URL 与 Token 请求
 * 3：签名检测
 * 注意： MsgController 的继承类如果覆盖了 index 方法，则需要对该 index 方法声明该拦截器
 * 因为子类覆盖父类方法会使父类方法配置的拦截器失效，从而失去本拦截器的功能
 */
public class AuthMessageInterceptor implements Interceptor {

    private static final Log log = Log.getLog(AuthMessageInterceptor.class);

    public void intercept(Invocation inv) {
        Controller controller = inv.getController();
        if (controller instanceof AuthMessageController == false)
            throw new RuntimeException("控制器需要继承 AuthMessageController");

        try {
            // 将 ApiConfig 对象与当前线程绑定，以便在后续操作中方便获取该对象： ApiConfigKit.getComponentApiConfig();
            ApiConfigKit.setThreadLocalComponentApiConfig(((AuthMessageController) controller).getComponentApiConfig());


            // 对开发测试更加友好
            if (ApiConfigKit.isDevMode()) {
                inv.invoke();
            } else {
                // 签名检测
                if (checkSignature(controller)) {
                    inv.invoke();
                } else {
                    controller.renderText("签名验证失败，请确定是微信服务器在发送消息过来");
                }
            }

        } finally {
            ApiConfigKit.removeThreadLocalComponentApiConfig();
        }
    }

    /**
     * 检测签名
     */
    private boolean checkSignature(Controller controller) {
        String signature = controller.getPara("signature");
        String timestamp = controller.getPara("timestamp");
        String nonce     = controller.getPara("nonce");
        if (StrKit.isBlank(signature) || StrKit.isBlank(timestamp) || StrKit.isBlank(nonce)) {
            controller.renderText("check signature failure");
            return false;
        }

        if (SignatureCheckKit.me.checkSignature(signature, timestamp, nonce)) {
            return true;
        } else {
            log.error("check signature failure: " +
                      " signature = " + controller.getPara("signature") +
                      " timestamp = " + controller.getPara("timestamp") +
                      " nonce = " + controller.getPara("nonce"));

            return false;
        }
    }


}



