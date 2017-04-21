package com.jfinal.weixin.iot.msg;

import com.jfinal.weixin.sdk.msg.in.InMsg;
import com.jfinal.weixin.sdk.msg.out.OutMsg;

/**
<xml>
<ToUserName><![CDATA[%s]]></ToUserName>
<FromUserName><![CDATA[%s]]></FromUserName>
<CreateTime>%u</CreateTime>
<MsgType><![CDATA[%s]]></MsgType>
<DeviceType><![CDATA[%s]]></DeviceType>
<DeviceID><![CDATA[%s]]></DeviceID>
<SessionID>%u</SessionID>
<Content><![CDATA[%s]]></Content>
</xml>
*/
public class OutEquDataMsg extends OutMsg {
	private static final long serialVersionUID = -1187439400934008473L;

	private String DeviceType;
	private String DeviceID;
	private String content;
	private String SessionID;


	public OutEquDataMsg(InMsg inMsg) {
		super(inMsg);
		this.msgType = "device_text";
	}

	public OutEquDataMsg() {
		this.msgType = "device_text";
	}

	@Override
	protected void subXml(StringBuilder sb) {
		if (null == content) {
			throw new NullPointerException("content is null");
		}
		sb.append("<DeviceType><![CDATA[").append(DeviceType).append("]]></DeviceType>\n");
		sb.append("<DeviceID><![CDATA[").append(DeviceID).append("]]></DeviceID>\n");
		sb.append("<SessionID><![CDATA[").append(SessionID).append("]]></SessionID>\n");
		sb.append("<Content><![CDATA[").append(content).append("]]></Content>\n");
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
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSessionID() {
		return SessionID;
	}

	public void setSessionID(String sessionID) {
		SessionID = sessionID;
	}
}
