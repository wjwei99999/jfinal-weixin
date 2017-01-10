package com.jfinal.wxaapp.msg;

import com.jfinal.weixin.sdk.utils.JsonUtils;
import com.jfinal.wxaapp.msg.bean.WxaMsg;

public class JsonMsgParser implements IMsgParser {

	@Override
	public WxaMsg parser(String msgStr) {
		MsgModel msgModel = JsonUtils.parse(msgStr, MsgModel.class);
		System.out.println(msgModel);
		return null;
	}

}
