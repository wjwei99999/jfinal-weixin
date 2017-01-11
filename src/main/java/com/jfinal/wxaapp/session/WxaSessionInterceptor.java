/**
 * Copyright (c) 2011-2014, L.cm 卢春梦 (qq596392912@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.wxaapp.session;

import java.util.Map;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.wxaapp.WxaConfigKit;
import com.jfinal.wxaapp.jfinal.WxaController;

/**
 * 微信小程序session存储管理拦截器
 * @author L.cm
 *
 */
public class WxaSessionInterceptor implements Interceptor {
	
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
		// sessionId isBlank
		if (StrKit.isBlank(waxSessionId)) {
			Map<Object, Object> errorMap = Ret.create("errcode", 500)
					.put("errmsg", "waxSessionId isBlank!")
					.getData();
			controller.renderJson(errorMap);
			return;
		}
		WxaSessionWrapper sessionWrapper = new WxaSessionWrapper(controller.getRequest(), waxSessionId);
		controller.setHttpServletRequest(sessionWrapper);
		inv.invoke();
	}

}
