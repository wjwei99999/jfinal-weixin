/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.msg.out;

import com.jfinal.weixin.sdk.msg.in.InMsg;

/**
	回复图片消息
	<xml>
		<ToUserName><![CDATA[toUser]]></ToUserName>
		<FromUserName><![CDATA[fromUser]]></FromUserName>
		<CreateTime>12345678</CreateTime>
		<MsgType><![CDATA[image]]></MsgType>
			<Image>
				<MediaId><![CDATA[media_id]]></MediaId>
			</Image>
	</xml>
 */
public class OutImageMsg extends OutMsg {
	
	private String mediaId;
	
	public OutImageMsg() {
		this.msgType = "image";
	}
	
	public OutImageMsg(InMsg inMsg) {
		super(inMsg);
		this.msgType = "image";
	}
	
	@Override
	protected String subXml() {
		return "<Image>\n"
				+ "<MediaId><![CDATA[" + mediaId + "]]></MediaId>\n"
			+  "</Image>\n";
	}
	
	public String getMediaId() {
		return mediaId;
	}
	
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

}



