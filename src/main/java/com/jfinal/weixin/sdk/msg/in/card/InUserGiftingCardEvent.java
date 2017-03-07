package com.jfinal.weixin.sdk.msg.in.card;

import com.jfinal.weixin.sdk.msg.in.event.EventInMsg;
import com.jfinal.weixin.sdk.utils.XmlHelper;

/**
 * 转赠事件推送
<xml>
  <ToUserName><![CDATA[gh_3fcea188bf78]]></ToUserName>
  <FromUserName><![CDATA[obLatjjwDolFjRRd3doGIdwNqRXw]]></FromUserName>
  <CreateTime>1474181868</CreateTime>
  <MsgType><![CDATA[event]]></MsgType>
  <Event><![CDATA[user_gifting_card]]></Event>
  <CardId><![CDATA[pbLatjhU-3pik3d4PsbVzvBxZvJc]]></CardId>
  <UserCardCode><![CDATA[297466945104]]></UserCardCode>
  <IsReturnBack>0</IsReturnBack>
  <FriendUserName><![CDATA[obLatjlNerkb62HtSdQUx66C4NTU]]></FriendUserName>
  <IsChatRoom>0</IsChatRoom>
</xml>
 */
@SuppressWarnings("serial")
public class InUserGiftingCardEvent extends EventInMsg implements ICardMsgParse {
    public static final String EVENT = "user_gifting_card";

    private String cardId;
    private String userCardCode;
    private String isReturnBack;
    private String friendUserName;
    private String isChatRoom;

    public InUserGiftingCardEvent(String toUserName, String fromUserName, Integer createTime) {
        super(toUserName, fromUserName, createTime, EVENT);
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getUserCardCode() {
        return userCardCode;
    }

    public void setUserCardCode(String userCardCode) {
        this.userCardCode = userCardCode;
    }

    public String getIsReturnBack() {
        return isReturnBack;
    }

    public void setIsReturnBack(String isReturnBack) {
        this.isReturnBack = isReturnBack;
    }

    public String getFriendUserName() {
        return friendUserName;
    }

    public void setFriendUserName(String friendUserName) {
        this.friendUserName = friendUserName;
    }

    public String getIsChatRoom() {
        return isChatRoom;
    }

    public void setIsChatRoom(String isChatRoom) {
        this.isChatRoom = isChatRoom;
    }

    @Override
    public void parse(XmlHelper xmlHelper) {
        setCardId(xmlHelper.getString("//CardId"));
        setUserCardCode(xmlHelper.getString("//UserCardCode"));
    }
}
