package com.jfinal.weixin.sdk.msg.in.card;

import com.jfinal.weixin.sdk.msg.in.event.EventInMsg;

/**
 * 库存报警事件
 * @author L.cm
<xml>
  <ToUserName><![CDATA[gh_2d62d*****0]]></ToUserName>
  <FromUserName><![CDATA[oa3LFuBvWb7*********]]></FromUserName>
  <CreateTime>1443838506</CreateTime>
  <MsgType><![CDATA[event]]></MsgType>
  <Event><![CDATA[card_sku_remind]]></Event>
  <CardId><![CDATA[pa3LFuAh2P65**********]]></CardId>
  <Detail><![CDATA[the card's quantity is equal to 0]]></Detail>
</xml>
 */
@SuppressWarnings("serial")
public class InCardSkuRemindEvent extends EventInMsg {
	public static final String EVENT = "card_pay_order";

	public InCardSkuRemindEvent(String toUserName, String fromUserName, Integer createTime) {
		super(toUserName, fromUserName, createTime, EVENT);
	}
	
	private String cardId; //卡券ID
	private String detail; //报警详细信息

	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
}
