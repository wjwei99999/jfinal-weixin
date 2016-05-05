package com.jfinal.weixin.sdk.msg.in.event;

/**
 * Created by L.cm on 2016/5/5.
 * 微信会员卡快速买单
 *
 // <xml>
 // <ToUserName><![CDATA[gh_7638cbc70355]]></ToUserName>
 // <FromUserName><![CDATA[o_CBes-OUGtQ4vxd_7r5-p5QRRXU]]></FromUserName>
 // <CreateTime>1462420332</CreateTime>
 // <MsgType><![CDATA[event]]></MsgType>
 // <Event><![CDATA[user_pay_from_pay_cell]]></Event>
 // <CardId><![CDATA[p_CBes55910LQGAOStjVKaTChpsg]]></CardId>
 // <UserCardCode><![CDATA[777670435071]]></UserCardCode>
 // <TransId><![CDATA[4001802001201605055526028099]]></TransId>
 // <LocationId>403808221</LocationId>
 // <Fee>100</Fee>
 // <OriginalFee>100</OriginalFee>
 // </xml>
 */
public class InUserPayFromCardEvent extends EventInMsg {
    public static final String EVENT = "user_pay_from_pay_cell";

    private String cardId;
    private String userCardCode;
    private String transId;
    private String locationId;
    private String fee;
    private String originalFee;

    public InUserPayFromCardEvent(String toUserName, String fromUserName, Integer createTime, String msgType, String event) {
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

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getOriginalFee() {
        return originalFee;
    }

    public void setOriginalFee(String originalFee) {
        this.originalFee = originalFee;
    }
}
