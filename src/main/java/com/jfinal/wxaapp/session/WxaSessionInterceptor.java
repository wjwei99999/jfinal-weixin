/**
 * Copyright (c) 2011-2014, L.cm 卢春梦 (qq596392912@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.wxaapp.session;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.UUID;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.wxaapp.WxaConfigKit;
import com.jfinal.wxaapp.jfinal.WxaController;

/**
 * 微信小程序session存储管理拦截器
 * @author L.cm
 *
 */
public class WxaSessionInterceptor implements Interceptor {
	private static Log log = Log.getLog(WxaSessionInterceptor.class);

	@Override
	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		if (!(controller instanceof WxaController)) {
			throw new RuntimeException("控制器需要继承 WxaController");
		}
		String sessionIdName = WxaConfigKit.getSessionIdName();
		// 首先尝试从Header中获取sessionId
		String waxSessionId = controller.getRequest().getHeader(sessionIdName);
		if (StrKit.isBlank(waxSessionId)) {
			waxSessionId = controller.getPara(sessionIdName);
		}
		Method method = inv.getMethod();
		SkipWaxSession skipSession = method.getAnnotation(SkipWaxSession.class);
		// sessionId isBlank
		if (StrKit.isBlank(waxSessionId)) {
			if (skipSession == null) {
				Map<Object, Object> data = Ret.create("errcode", 500)
						.put("errmsg", "code is blank")
						.getData();
				controller.renderJson(data);
				return;
			}
			waxSessionId = UUID.randomUUID().toString();
			log.error("waxSessionId isBlank! 我们猜测你只是想执行登陆！ 故新生成了waxSessionId: " + waxSessionId);
		}
		WxaSessionWrapper sessionWrapper = new WxaSessionWrapper(controller.getRequest(), waxSessionId);
		controller.setHttpServletRequest(sessionWrapper);
		inv.invoke();
	}

}
