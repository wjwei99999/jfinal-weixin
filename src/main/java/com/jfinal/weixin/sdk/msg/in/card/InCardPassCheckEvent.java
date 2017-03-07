package com.jfinal.weixin.sdk.msg.in.card;

import com.jfinal.weixin.sdk.msg.in.event.EventInMsg;
import com.jfinal.weixin.sdk.utils.XmlHelper;

/**
 * 审核事件推送
 * @author L.cm
<xml>
  <ToUserName><![CDATA[toUser]]></ToUserName>
  <FromUserName><![CDATA[FromUser]]></FromUserName>
  <CreateTime>123456789</CreateTime>
  <MsgType><![CDATA[event]]></MsgType>
  <Event><![CDATA[card_pass_check]]></Event> //不通过为card_not_pass_check
  <CardId><![CDATA[cardid]]></CardId>
  <RefuseReason><![CDATA[非法代制]]></RefuseReason>
</xml>
 */
@SuppressWarnings("serial")
public class InCardPassCheckEvent extends EventInMsg implements ICardMsgParse{
    public static final String EVENT_PASS = "card_pass_check";
    public static final String EVENT_NOT_PASS = "card_not_pass_check";
    
    /**
     * 卡券ID
     */
    private String cardId;
    /**
     * 审核不通过原因
     */
    private String refuseReason;

    public InCardPassCheckEvent(String toUserName, String fromUserName, Integer createTime, String event) {
        super(toUserName, fromUserName, createTime, event);
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    @Override
    public void parse(XmlHelper xmlHelper) {
        setCardId(xmlHelper.getString("//CardId"));
        setRefuseReason(xmlHelper.getString("//RefuseReason"));
    }
}
