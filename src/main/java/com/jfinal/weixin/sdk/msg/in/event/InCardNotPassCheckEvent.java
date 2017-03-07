package com.jfinal.weixin.sdk.msg.in.event;

@SuppressWarnings("serial")
public class InCardNotPassCheckEvent extends InCardPassCheckEvent {
	public static final String EVENT = "card_not_pass_check";
	
	public InCardNotPassCheckEvent(String toUserName, String fromUserName, Integer createTime, String msgType, String event) {
		super(toUserName, fromUserName, createTime, msgType, event);
	}
}
