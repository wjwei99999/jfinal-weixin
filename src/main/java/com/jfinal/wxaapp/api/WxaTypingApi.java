package com.jfinal.wxaapp.api;

import com.jfinal.kit.Kv;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.jfinal.weixin.sdk.utils.JsonUtils;
/**
  * 微信小程序客服消息界面向客户端发送输入状态
 * @author: 山东小木
 */
public class WxaTypingApi {
	private static String setTypingUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/typing?access_token=";
	/**
	  * 发送commond
	 * @param openId
	 * @param commond
	 * @return
	 */
	private static ApiResult sendCommond(String openId,String commond) {
		String accessToken = WxaAccessTokenApi.getAccessTokenStr();
		Kv kv=Kv.by("touser", openId).set("command",commond);
		String jsonResult = HttpUtils.post(setTypingUrl + accessToken, JsonUtils.toJson(kv));
		return new ApiResult(jsonResult);
	}
	
	/**
	 * 下发输入状态
	 * @param openId
	 * @return
	 */
	public static ApiResult setTyping(String openId) {
		return sendCommond(openId,"Typing");
	}
	/**
	  * 取消输入状态
	 * @param openId
	 * @return
	 */
	public static ApiResult cancelTyping(String openId) {
		return sendCommond(openId,"CancelTyping");
	}
}
