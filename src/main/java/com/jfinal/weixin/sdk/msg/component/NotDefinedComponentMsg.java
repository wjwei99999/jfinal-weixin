package com.jfinal.weixin.sdk.msg.component;

/**
 * 没有找到对应的消息类型
 */
public class NotDefinedComponentMsg extends AuthMsg {

    public NotDefinedComponentMsg(String appId, Integer createTime, String infoType) {
        super(appId, createTime, infoType);
    }
}
