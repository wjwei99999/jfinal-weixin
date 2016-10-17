package com.jfinal.weixin.sdk.jfinal.component;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.weixin.sdk.api.ApiConfigKit;

/**
 * ApiController 为 ApiController 绑定 ApiConfig 对象到当前线程，
 * 以便在后续的操作中可以使用 ApiConfigKit.getApiConfig() 获取到该对象
 */
public class ComponentAPIInterceptor implements Interceptor {

    public void intercept(Invocation inv) {
        Controller controller = inv.getController();
        if (controller instanceof ComponentAPIController == false)
            throw new RuntimeException("控制器需要继承 ApiController");

        try {
            ApiConfigKit.setThreadLocalComponentApiConfig(((ComponentAPIController) controller).getApiConfig());
            inv.invoke();
        } finally {
            ApiConfigKit.removeThreadLocalComponentApiConfig();
        }
    }
}

