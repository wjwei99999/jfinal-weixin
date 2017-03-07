package com.jfinal.weixin.sdk.msg.in.event;

/**
 * 
 * @author L.cm
<xml> 
  <ToUserName><![CDATA[toUser]]></ToUserName>  
  <FromUserName><![CDATA[FromUser]]></FromUserName>  
  <CreateTime>123456789</CreateTime>  
  <MsgType><![CDATA[event]]></MsgType>  
  <Event><![CDATA[user_enter_session_from_card]]></Event>  
  <CardId><![CDATA[cardid]]></CardId>  
  <UserCardCode><![CDATA[12312312]]></UserCardCode> 
</xml>
 */
@SuppressWarnings("serial")
public class InUserEnterSessionFromCardEvent extends EventInMsg {
	public static final String EVENT = "user_enter_session_from_card";

	public InUserEnterSessionFromCardEvent(String toUserName, String fromUserName, Integer createTime, String msgType,
			String event) {
		super(toUserName, fromUserName, createTime, msgType, event);
	}
}
