package com.jfinal.weixin.demo;

import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.component.ComponentAccessTokenApi;
import com.jfinal.weixin.sdk.api.component.ComponentSnsAccessToken;
import com.jfinal.weixin.sdk.api.component.ComponentSnsAccessTokenApi;
import com.jfinal.weixin.sdk.jfinal.component.ComponentAPIController;

/**
 * 代公众号发起网页授权
 *
 */
public class WeixinComponentSnsController extends ComponentAPIController {

	private static final Log log = Log.getLog(WeixinComponentSnsController.class);
	
	@Override
	public ApiConfig getApiConfig() {
		return ApiConfigKit.getApiConfig();
	}
	
	/**
	 * 组织代公众号网页授权 URL，传到前端  HTML 发起请求 
	 */
	public void index() {
		
		String authorizerAppId = getPara("authorizer_appid");
		if(!StrKit.isBlank(authorizerAppId)){
			String redirect_uri = "http%3a%2f%2f" + getRequest().getServerName() + "%2fcomponentsns%2fcallback";
			String authorizeURL = ComponentSnsAccessTokenApi.getAuthorizeURL(authorizerAppId, redirect_uri, ApiConfigKit.getAppId(), true);
			log.info("authorizeURL : " + authorizeURL);
			setAttr("replaceActionUrl", authorizeURL);
		    render("/_front/common/location_replace.html");
		} else {
			renderNull();
		}
    }
	
	/**
	 * 代公众号发起网页授权的回调处理
	 */
	public void callback() {
		
		if(!StrKit.isBlank(getPara("code")) && !StrKit.isBlank(getPara("appid"))){
			ComponentSnsAccessToken sns = ComponentSnsAccessTokenApi.getSnsAccessToken(getPara("appid"), getPara("code"), ApiConfigKit.getAppId(),ComponentAccessTokenApi.getComponentAccessTokenStr());
			log.info("代公众号发起网页授权，获得 openid >> " + sns.getOpenid());
		}

		// TODO 网页授权成功页面
		renderNull();
		
    }
	
}
