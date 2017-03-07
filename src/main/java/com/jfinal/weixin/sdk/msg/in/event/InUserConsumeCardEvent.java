package com.jfinal.weixin.sdk.msg.in.event;

/**
 * 
 * @author L.cm
<xml> 
  <ToUserName> <![CDATA[gh_fc0a06a20993]]> </ToUserName>  
  <FromUserName> <![CDATA[oZI8Fj040-be6rlDohc6gkoPOQTQ]]> </FromUserName>  
  <CreateTime>1472549042</CreateTime>  
  <MsgType> <![CDATA[event]]> </MsgType>  
  <Event> <![CDATA[user_consume_card]]> </Event>  
  <CardId> <![CDATA[pZI8Fj8y-E8hpvho2d1ZvpGwQBvA]]> </CardId>  
  <UserCardCode> <![CDATA[452998530302]]> </UserCardCode>  
  <ConsumeSource> <![CDATA[FROM_API]]> </ConsumeSource>  
  <LocationName> <![CDATA[]]> </LocationName>  
  <StaffOpenId> <![CDATA[oZ********nJ3bPJu_Rtjkw4c]]> </StaffOpenId>  
  <VerifyCode> <![CDATA[]]> </VerifyCode>  
  <RemarkAmount> <![CDATA[]]> </RemarkAmount>  
  <OuterStr> <![CDATA[xxxxx]]> </OuterStr> 
</xml>
 */
@SuppressWarnings("serial")
public class InUserConsumeCardEvent extends EventInMsg {
	public static final String EVENT = "user_consume_card";

	public InUserConsumeCardEvent(String toUserName, String fromUserName, Integer createTime, String msgType,
			String event) {
		super(toUserName, fromUserName, createTime, msgType, event);
	}
}
