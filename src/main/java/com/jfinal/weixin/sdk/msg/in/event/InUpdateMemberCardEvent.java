package com.jfinal.weixin.sdk.msg.in.event;

/**
 * Created by L.cm on 2016/5/5.
 * 微信会员卡积分变更
 *
 // <xml>
 // <ToUserName><![CDATA[gh_7638cbc70355]]></ToUserName>
 // <FromUserName><![CDATA[o_CBes-OUGtQ4vxd_7r5-p5QRRXU]]></FromUserName>
 // <CreateTime>1462420730</CreateTime>
 // <MsgType><![CDATA[event]]></MsgType>
 // <Event><![CDATA[update_member_card]]></Event>
 // <CardId><![CDATA[p_CBes55910LQGAOStjVKaTChpsg]]></CardId>
 // <UserCardCode><![CDATA[777670435071]]></UserCardCode>
 // <ModifyBonus>1000</ModifyBonus>
 // <ModifyBalance>0</ModifyBalance>
 // </xml>
 */
public class InUpdateMemberCardEvent extends EventInMsg {
    public static final String EVENT = "update_member_card";

    private String cardId;
    private String userCardCode;
    private String modifyBonus;
    private String modifyBalance;

    public InUpdateMemberCardEvent(String toUserName, String fromUserName, Integer createTime, String msgType, String event) {
        super(toUserName, fromUserName, createTime, msgType, event);
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

    public String getModifyBalance() {
        return modifyBalance;
    }

    public void setModifyBalance(String modifyBalance) {
        this.modifyBalance = modifyBalance;
    }

    public String getModifyBonus() {
        return modifyBonus;
    }

    public void setModifyBonus(String modifyBonus) {
        this.modifyBonus = modifyBonus;
    }
}
