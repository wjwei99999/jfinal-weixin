package com.jfinal.weixin.sdk.session;

/**
 * session管理类
 * @author L.cm
 */
public interface WxSessionManager {
    /**
     * 默认为session_key的超时时间
     */
    int TIME_OUT = 2592000 - 5;
    
    /**
     * 获取WxSession
     * @param sessionId sessionId
     * @return {WxSession}
     */
    WxSession get(String sessionId);
    
    /**
     * 保存 WxSession
     * @param session WxSession
     */
    void save(WxSession session);
    
    /**
     * 更新 WxSession
     * @param session WxSession
     */
    void update(WxSession session);
}
