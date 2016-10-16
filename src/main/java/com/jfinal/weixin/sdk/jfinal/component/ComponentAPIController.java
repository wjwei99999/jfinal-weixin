package com.jfinal.weixin.sdk.jfinal.component;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.weixin.sdk.api.ApiConfig;

/**
 * 所有使用 Api 的 controller 需要继承此类
 */
@Before(ComponentAPIInterceptor.class)
public abstract class ComponentAPIController extends Controller {
    public abstract ApiConfig getApiConfig();
}
