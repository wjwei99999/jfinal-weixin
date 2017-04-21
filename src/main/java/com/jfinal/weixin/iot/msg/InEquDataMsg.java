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
	
	private String DeviceType;
	private String DeviceID;
	private String Content;
	private String msgId;
	private String SessionID;
	private String OpenID;
	
	public InEquDataMsg(String toUserName, String fromUserName,	Integer createTime, String msgType) {
		super(toUserName, fromUserName, createTime, msgType);

	}

	public String getDeviceType() {
		return DeviceType;
	}

	public void setDeviceType(String deviceType) {
		DeviceType = deviceType;
	}

	public String getDeviceID() {
		return DeviceID;
	}

	public void setDeviceID(String deviceID) {
		DeviceID = deviceID;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		this.Content = content;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getSessionID() {
		return SessionID;
	}

	public void setSessionID(String sessionID) {
		SessionID = sessionID;
	}

	public String getOpenID() {
		return OpenID;
	}

	public void setOpenID(String openID) {
		OpenID = openID;
	}
}