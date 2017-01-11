/**
 * Copyright (c) 2011-2014, L.cm 卢春梦 (qq596392912@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

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
