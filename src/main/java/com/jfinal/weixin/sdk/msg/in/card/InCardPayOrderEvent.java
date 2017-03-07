package com.jfinal.weixin.sdk.msg.in.card;

import com.jfinal.weixin.sdk.msg.in.event.EventInMsg;

/**
 *
 * @author L.cm
<xml>
  <ToUserName><![CDATA[gh_7223c83d4be5]]></ToUserName>
  <FromUserName><![CDATA[ob5E7s-HoN9tslQY3-0I4qmgluHk]]></FromUserName>
  <CreateTime>1453295737</CreateTime>
  <MsgType><![CDATA[event]]></MsgType>
  <Event><![CDATA[card_pay_order]]></Event>
  <OrderId><![CDATA[404091456]]></OrderId>
  <Status><![CDATA[ORDER_STATUS_FINANCE_SUCC]]></Status>
  <CreateOrderTime>1453295737</CreateOrderTime>
  <PayFinishTime>0</PayFinishTime>
  <Desc><![CDATA[]]></Desc>
  <FreeCoinCount><![CDATA[200]]></FreeCoinCount>
  <PayCoinCount><![CDATA[0]]></PayCoinCount>
  <RefundFreeCoinCount><![CDATA[0]]></RefundFreeCoinCount>
  <RefundPayCoinCount><![CDATA[0]]></RefundPayCoinCount>
  <OrderType><![CDATA[ORDER_TYPE_SYS_ADD]]></OrderType>
  <Memo><![CDATA[开通账户奖励]]></Memo>
  <ReceiptInfo><![CDATA[]]></ReceiptInfo>
</xml>
 */
@SuppressWarnings("serial")
public class InCardPayOrderEvent extends EventInMsg {
	public static final String EVENT = "card_pay_order";

	public InCardPayOrderEvent(String toUserName, String fromUserName, Integer createTime, String msgType,
			String event) {
		super(toUserName, fromUserName, createTime, msgType, event);
	}

}
