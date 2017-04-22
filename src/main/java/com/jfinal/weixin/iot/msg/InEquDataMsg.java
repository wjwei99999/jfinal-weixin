package com.jfinal.weixin.iot.msg;

import com.jfinal.weixin.sdk.msg.in.InMsg;

/**
<xml>
<ToUserName><![CDATA[%s]]></ToUserName>
<FromUserName><![CDATA[%s]]></FromUserName>
<CreateTime>%u</CreateTime>
<MsgType><![CDATA[%s]]></MsgType>
<DeviceType><![CDATA[%s]]></DeviceType>
<DeviceID><![CDATA[%s]]></DeviceID>
<Content><![CDATA[%s]]></Content>
<SessionID>%lu</SessionID>
<MsgID>%lu</MsgID>
<OpenID><![CDATA[%s]]></OpenID>
</xml>
*/
public class InEquDataMsg extends InMsg {
	private static final long serialVersionUID = 8591751084774828448L;
	// 消息
	public static final String DEVICE_TEXT = "device_text";
	
	private String deviceType;
	private String deviceID;
	private String content;
	private String msgId;
	private String sessionID;
	private String openID;
	
	public InEquDataMsg(String toUserName, String fromUserName,	Integer createTime, String msgType) {
		super(toUserName, fromUserName, createTime, msgType);
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}
}