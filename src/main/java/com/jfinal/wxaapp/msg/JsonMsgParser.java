package com.jfinal.wxaapp.msg;

import com.jfinal.weixin.sdk.utils.JsonUtils;

public class JsonMsgParser implements IMsgParser {

	@Override
	public WxaMsg parser(String msgStr) {
		return JsonUtils.parse(msgStr, WxaMsg.class);
	}

	public static void main(String[] args) {
		String json = "{\"ToUserName\": \"toUser\",\"FromUserName\": \"fromUser\",\"CreateTime\": 1482048670,\"MsgType\": \"event\",\"Event\": \"user_enter_tempsession\",\"SessionFrom\": \"sessionFrom\"}";
		
		WxaMsg wxaMsg = new JsonMsgParser().parser(json);
		
		System.out.println(wxaMsg);
	}
}
