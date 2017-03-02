package com.jfinal.weixin.demo;

import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.jfinal.component.AuthMessageControllerAdapter;

/**
 * 接收微信服务器消息，自动解析成 InMsg 并分发到相应的处理方法<br>
 * 消息类型：<br>
 * component_verify_ticket 微信服务器每隔10分钟会向第三方的消息接收地址推送一次component_verify_ticket，用于获取第三方平台接口调用凭据<br>
 * authorized 授权成功通知<br>
 * unauthorized 取消授权通知<br>
 * updateauthorized 授权更新通知<br>
 */
public class WeixinAuthMessageController extends AuthMessageControllerAdapter {

	public ApiConfig getComponentApiConfig() {
		return ApiConfigKit.getApiConfig();
	}
	
}
