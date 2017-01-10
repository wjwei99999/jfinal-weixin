/**
 * Copyright (c) 2011-2014, L.cm 卢春梦 (qq596392912@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.wxaapp.api;

import com.jfinal.weixin.sdk.utils.HttpUtils;

/**
 * 小程序access_token
 * @author L.cm
 *
 */
public class WxaAccessTokenApi {
	private static String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appid}&secret={secret}";
	
	public static void main(String[] args) {
		String url = tokenUrl.replace("{appid}", "wx9114b997bd86f8ed").replace("{secret}", "d27551c7803cf16015e536b192d5d03b");
		System.out.println(HttpUtils.get(url));
		
//		{"access_token":"dlNTTwvbFBf6PeEdh6JbwmJDwJCy7zbg4XYrFYhmGYCyuoLO5N8GMQ2HbBuzzJRhzzKC0haP0vu2uV3tHC6ptsLFKnOO3Ne8lI6_QqchqtrdjSkKixEpdJldbfjM4-QtZNKfAHALZW","expires_in":7200}
	}
	
}
