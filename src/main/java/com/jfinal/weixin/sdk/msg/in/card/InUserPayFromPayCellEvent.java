package com.jfinal.weixin.sdk.msg.in.card;

import com.jfinal.weixin.sdk.msg.in.event.EventInMsg;

/**
 *
 * @author L.cm
<xml>
  <ToUserName><![CDATA[gh_e2243xxxxxxx]]></ToUserName>
  <FromUserName><![CDATA[oo2VNuOUuZGMxxxxxxxx]]></FromUserName>
  <CreateTime>1442390947</CreateTime>
  <MsgType><![CDATA[event]]></MsgType>
  <Event><![CDATA[user_pay_from_pay_cell]]></Event>
  <CardId><![CDATA[po2VNuCuRo-8sxxxxxxxxxxx]]></CardId>
  <UserCardCode><![CDATA[38050000000]]></UserCardCode>
  <TransId><![CDATA[10022403432015000000000]]></TransId>
  <LocationId>291710000</LocationId>
  <Fee><![CDATA[10000]]></Fee>
  <OriginalFee><![CDATA[10000]]> </OriginalFee>
</xml>
 */
@SuppressWarnings("serial")
public class InUserPayFromPayCellEvent extends EventInMsg {
	public static final String EVENT = "user_pay_from_pay_cell";

	public InUserPayFromPayCellEvent(String toUserName, String fromUserName, Integer createTime, String msgType,
			String event) {
		super(toUserName, fromUserName, createTime, msgType, event);
	}
}
