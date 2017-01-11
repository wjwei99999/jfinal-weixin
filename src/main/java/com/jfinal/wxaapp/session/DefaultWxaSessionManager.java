/**
 * Copyright (c) 2011-2014, L.cm 卢春梦 (qq596392912@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.wxaapp.session;

import com.jfinal.weixin.sdk.session.cache.TimedCache;

/**
 * 默认的Session管理器
 * @author L.cm
 */
public class DefaultWxaSessionManager implements WxaSessionManager {
    private final TimedCache<String, WxaSession> timedCache;
    
    public DefaultWxaSessionManager() {
        this.timedCache = new TimedCache<String, WxaSession>(TIME_OUT * 60 * 1000l);
    }

    @Override
    public WxaSession get(String sessionId) {
        return timedCache.get(sessionId);
    }

    @Override
    public void save(WxaSession session) {
        timedCache.put(session.id, session);
    }

    @Override
    public void update(WxaSession session) {
        save(session);
    }

}