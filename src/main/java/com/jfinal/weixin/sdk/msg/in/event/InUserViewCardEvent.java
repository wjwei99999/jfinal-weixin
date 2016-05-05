package com.jfinal.weixin.sdk.msg.in.event;

/**
 * Created by L.cm on 2016/5/5.
 * 微信会员卡二维码扫描领取接口
 *
 // <xml>
 // <ToUserName><![CDATA[gh_7638cbc70355]]></ToUserName>
 // <FromUserName><![CDATA[o_CBes-OUGtQ4vxd_7r5-p5QRRXU]]></FromUserName>
 // <CreateTime>1462420243</CreateTime>
 // <MsgType><![CDATA[event]]></MsgType>
 // <Event><![CDATA[user_view_card]]></Event>
 // <CardId><![CDATA[p_CBes55910LQGAOStjVKaTChpsg]]></CardId>
 // <UserCardCode><![CDATA[777670435071]]></UserCardCode>
 // </xml>
 *
 */
public class InUserViewCardEvent extends EventInMsg {
    public static final String EVENT = "user_view_card";

    private String cardId;
    private String userCardCode;

    public InUserViewCardEvent(String toUserName, String fromUserName, Integer createTime, String msgType, String event) {
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
}
