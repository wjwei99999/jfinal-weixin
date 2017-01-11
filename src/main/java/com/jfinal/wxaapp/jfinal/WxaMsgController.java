package com.jfinal.wxaapp.jfinal;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * 小程序消息控制器
 * @author L.cm
 *
 */
public abstract class WxaMsgController extends Controller  {

	@Before(WxaMsgInterceptor.class)
	public void index() {
		
	}

}
