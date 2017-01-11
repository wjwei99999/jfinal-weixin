package com.jfinal.wxaapp.demo;

import com.jfinal.aop.Before;
import com.jfinal.wxaapp.jfinal.WxaController;
import com.jfinal.wxaapp.jfinal.WxaSessionInterceptor;

@Before(WxaSessionInterceptor.class)
public class DemoController extends WxaController {
	
	public void index() {
		setSessionAttr("hh", "helloword");
		renderJson("ok");
	}
	
	public void test() {
		renderJson(getSessionAttr("hh"));
	}
}
