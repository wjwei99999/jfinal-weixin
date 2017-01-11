package com.jfinal.wxaapp.session;

public interface WxaSessionManager {
    /**
     * 默认30分钟的缓存
     */
    int TIME_OUT = 30;
    /**
     * 获取WxaSession
     * @param sessionId sessionId
     * @return {WxaSession}
     */
	WxaSession get(String sessionId);
    
    /**
     * 保存 WxaSession
     * @param session WxaSession
     */
    void save(WxaSession session);
    
    /**
     * 更新 WxaSession
     * @param session WxaSession
     */
    void update(WxaSession session);
}
