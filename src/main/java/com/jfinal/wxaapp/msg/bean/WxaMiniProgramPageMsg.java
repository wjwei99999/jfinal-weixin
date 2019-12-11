package com.jfinal.wxaapp.msg.bean;

import com.jfinal.wxaapp.msg.MsgModel;

/**
 * 小程序卡片消息
 * @author 山东小木
 *
 */
public class WxaMiniProgramPageMsg extends WxaMsg {
    private static final long serialVersionUID = 7044451698431281586L;

    private String title;
    private String thumbUrl;
    private String thumbMediaId;
    private String appId;
    private String pagePath;
    private Long msgId;
    
    public WxaMiniProgramPageMsg(MsgModel msgModel) {
        super(msgModel);
        this.msgId = msgModel.getMsgId();
        this.title = msgModel.getTitle();
        this.thumbMediaId = msgModel.getThumbMediaId();
        this.thumbUrl = msgModel.getThumbUrl();
        this.appId = msgModel.getAppId();
        this.pagePath = msgModel.getPagePath();
    }

    public Long getMsgId() {
        return msgId;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}

	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getPagePath() {
		return pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}
}
