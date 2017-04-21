package com.jfinal.weixin.iot.msg;

import com.jfinal.weixin.sdk.msg.in.event.EventInMsg;

/**
 * lyb 微信硬件绑定和解绑事件类
 */
public class InEqubindEvent extends EventInMsg {
	private static final long serialVersionUID = 2579633450665411023L;
	// 事件
	public static final String DEVICE_EVENT = "device_event";
	// 设备具体事件类型
	public static class DeviceEvent {
		public static final String UNBIND = "unbind";
		public static final String BIND = "bind";
	}
	
	private String DeviceType;
	private String DeviceID;
	private String OpenID;	
	private String SessionID;
	
	public InEqubindEvent(String toUserName, String fromUserName, Integer createTime, String msgType, String event) {
		super(toUserName, fromUserName, createTime, msgType);
	}

	public String getSessionID() {
		return SessionID;
	}
	public void setSessionID(String sessionID) {
		SessionID = sessionID;
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
	public String getOpenID() {
		return OpenID;
	}
	public void setOpenID(String openID) {
		OpenID = openID;
	}
}