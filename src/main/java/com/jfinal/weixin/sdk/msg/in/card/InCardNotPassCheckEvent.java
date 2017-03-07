package com.jfinal.weixin.sdk.msg.in.card;

/**
 * 审核事件推送,审核不通过
 * @author L.cm
 */
@SuppressWarnings("serial")
public class InCardNotPassCheckEvent extends InCardPassCheckEvent {
	public static final String EVENT = "card_not_pass_check";

	public InCardNotPassCheckEvent(String toUserName, String fromUserName, Integer createTime, String msgType, String event) {
		super(toUserName, fromUserName, createTime, msgType, event);
	}
}
