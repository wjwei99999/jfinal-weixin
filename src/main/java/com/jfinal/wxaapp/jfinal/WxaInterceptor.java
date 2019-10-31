package com.jfinal.wxaapp.jfinal;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.weixin.sdk.jfinal.AppIdParser;
import com.jfinal.wxaapp.WxaConfigKit;

/**
 * WxaInterceptor 为 WxaController 绑定 WxaConfig 对象到当前线程，
 * 以便在后续的操作中可以使用 WxaConfigKit.getWxaConfig() 获取到该对象
 */
public class WxaInterceptor implements Interceptor {
    private static AppIdParser _parser = new AppIdParser.DefaultParameterAppIdParser();

    public static void setAppIdParser(AppIdParser parser) {
        _parser = parser;
    }

    public void intercept(Invocation inv) {
        Controller controller = inv.getController();
        if (!(controller instanceof WxaController))
            throw new RuntimeException("控制器需要继承 WxaController");

        try {
            String appId = _parser.getAppId(controller);
            // 将 appId 与当前线程绑定，以便在后续操作中方便获取WxaConfig对象： WxaConfigKit.getWxaConfig();
            WxaConfigKit.setThreadLocalAppId(appId);
            inv.invoke();
        } finally {
            WxaConfigKit.removeThreadLocalAppId();
        }
    }
}

