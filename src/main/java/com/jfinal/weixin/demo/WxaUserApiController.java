package com.jfinal.weixin.demo;

import com.jfinal.aop.Duang;
import com.jfinal.kit.JMap;
import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.wxaapp.api.WxaUserApi;
import com.jfinal.wxaapp.jfinal.WxaController;

/**
 * 微信小程序用户api接口
 * @author L.cm
 */
public class WxaUserApiController extends WxaController {
	// 微信用户接口api
	protected WxaUserApi wxaUserApi = Duang.duang(WxaUserApi.class);
	
	/**
	 * 登陆接口
	 */
	public void login() {
		String jsCode = getPara("code");
		if (StrKit.isBlank(jsCode)) {
			JMap data = JMap.create("errcode", 500)
					.set("errmsg", "code is blank");
			renderJson(data);
			return;
		}
		// 获取SessionKey
		ApiResult apiResult = wxaUserApi.getSessionKey(jsCode);
		// 返回{"session_key":"nzoqhc3OnwHzeTxJs+inbQ==","expires_in":2592000,"openid":"oVBkZ0aYgDMDIywRdgPW8-joxXc4"}
		if (!apiResult.isSucceed()) {
			renderJson(apiResult.getJson());
			return;
		}
		String sessionKey = apiResult.get("session_key");
		String openid = apiResult.get("openid");
		
		setSessionAttr("sessionKey", sessionKey);
		setSessionAttr("openid", openid);
		// 因为上面刚刚set，所以此处必有session
		String sessionId = getSession(false).getId();
		renderJson("sessionId", sessionId);
	}
	
	/**
	 * 服务端解密用户信息接口
	 * 获取unionId
	 */
	public void info() {
		String signature = getPara("signature");
		String rawData = getPara("rawData");
		
		String encryptedData = getPara("encryptedData");
		String iv = getPara("iv");
		
		// 参数空校验 不做演示
		
		// 获取sessionKey
		String sessionKey = getSessionAttr("sessionKey");
		if (StrKit.isBlank(sessionKey)) {
			JMap data = JMap.create("errcode", 500)
					.set("errmsg", "sessionKey is blank");
			renderJson(data);
			return;
		}
		// 用户信息校验
		boolean check = wxaUserApi.checkUserInfo(sessionKey, rawData, signature);
		if (!check) {
			JMap data = JMap.create("errcode", 500)
					.set("errmsg", "UserInfo check fail");
			renderJson(data);
			return;
		}
		// 服务端解密用户信息
		ApiResult apiResult = wxaUserApi.getUserInfo(sessionKey, encryptedData, iv);
		if (!apiResult.isSucceed()) {
			renderJson(apiResult.getJson());
			return;
		}
		// 如果开发者拥有多个移动应用、网站应用、和公众帐号（包括小程序），可通过unionid来区分用户的唯一性
		// 同一用户，对同一个微信开放平台下的不同应用，unionid是相同的。
		String unionId = apiResult.get("unionId");
		renderJson("{}");
	}

}
