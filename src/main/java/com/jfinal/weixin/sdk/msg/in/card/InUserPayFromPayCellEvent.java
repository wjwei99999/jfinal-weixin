package com.jfinal.weixin.sdk.msg.in.card;

import com.jfinal.weixin.sdk.msg.in.event.EventInMsg;
import com.jfinal.weixin.sdk.utils.XmlHelper;

/**
 * 买单事件推送
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
public class InUserPayFromPayCellEvent extends EventInMsg implements ICardMsgParse {
    public static final String EVENT = "user_pay_from_pay_cell";

    public InUserPayFromPayCellEvent(String toUserName, String fromUserName, Integer createTime) {
        super(toUserName, fromUserName, createTime, EVENT);
    }
    // 卡券ID。
    private String cardId;
    // 卡券Code码
    private String userCardCode;
    // 微信支付交易订单号（只有使用买单功能核销的卡券才会出现）
    private String transId;
    // 门店ID，当前卡券核销的门店ID（只有通过卡券商户助手和买单核销时才会出现）
    private String locationId;
    // 实付金额，单位为分
    private String fee;
    // 应付金额，单位为分
    private String originalFee;

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
    @Override
    public void parse(XmlHelper xmlHelper) {
        setCardId(xmlHelper.getString("//CardId"));
        setUserCardCode(xmlHelper.getString("//UserCardCode"));
    }
}
