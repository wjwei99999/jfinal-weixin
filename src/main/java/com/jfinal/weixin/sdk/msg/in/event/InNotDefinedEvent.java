package com.jfinal.weixin.sdk.msg.in.event;

import com.jfinal.weixin.sdk.utils.XmlHelper;

/**
 * 没有找到适配类型时的事件
 */
@SuppressWarnings("serial")
public class InNotDefinedEvent extends EventInMsg {
    /**
     * 新增xmlHelper，用于用户扩展。
     * 对于不支持的事件类型中，获取xml中想要的参数。
     */
    protected transient XmlHelper xmlHelper;
    
    public InNotDefinedEvent(String toUserName, String fromUserName, Integer createTime, String msgType, String event) {
        super(toUserName, fromUserName, createTime, msgType, event);
    }

    public XmlHelper getXmlHelper() {
        return xmlHelper;
    }

    public void setXmlHelper(XmlHelper xmlHelper) {
        this.xmlHelper = xmlHelper;
    }

}
