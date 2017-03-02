package com.jfinal.weixin.demo;

import com.jfinal.kit.JsonKit;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.component.ComponentAuthApi;
import com.jfinal.weixin.sdk.api.component.ComponentAuthorizerAccessToken;
import com.jfinal.weixin.sdk.jfinal.component.ComponentAPIController;

/**
 * 微信公众号授权，授权方扫描二维码授权给第三方平台
 *
 */
public class WeixinComponentAuthController extends ComponentAPIController {

	private static final Log log = Log.getLog(WeixinComponentAuthController.class);

	@Override
	public ApiConfig getApiConfig() {
		return ApiConfigKit.getApiConfig();
	}

	public void index() {
		String redirect_uri = "http%3a%2f%2f" + getRequest().getServerName() + "%2fauth%2fcallback";
		String authorizeURL = ComponentAuthApi.getAuthorizeURL(ApiConfigKit.getAppId(), redirect_uri);
		setAttr("replaceActionUrl", authorizeURL);
		render("/_front/common/location_replace.html");
	}

	/**
	 * 受理授权回调，使用授权码换取公众号的接口调用凭据和授权信息
	 */
	public void callback() {

		if (!StrKit.isBlank(getPara("auth_code"))) {

			String query_auth_code = getPara("auth_code");
			final ApiResult auth = ComponentAuthApi.auth(query_auth_code);

			if (auth != null && auth.isSucceed()) {
				final String authorization_info = JsonKit.toJson(auth.get("authorization_info"));

				log.info("auth json >> " + auth.toString());
				log.info("authorization_info >> " + authorization_info);

				ComponentAuthorizerAccessToken token = new ComponentAuthorizerAccessToken(authorization_info);

				log.info("授权方 AppId : " + token.getAuthorizerAppId());
				
			}

			// TODO 微信公众号授权成功页面
			renderNull();

		}

	}

}
