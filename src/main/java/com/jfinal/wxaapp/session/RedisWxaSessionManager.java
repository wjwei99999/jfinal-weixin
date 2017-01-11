/**
 * Copyright (c) 2011-2014, L.cm 卢春梦 (qq596392912@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.wxaapp.session;

import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;

/**
 * redis session 管理器
 * @author L.cm
 */
public class RedisWxaSessionManager implements WxaSessionManager {
    private final String SESSION_PREFIX = "jfinal-wxa:session:";

    private final Cache cache;

    public RedisWxaSessionManager() {
        this.cache = Redis.use();
    }

    public RedisWxaSessionManager(String cacheName) {
        this.cache = Redis.use(cacheName);
    }

    public RedisWxaSessionManager(Cache cache) {
        this.cache = cache;
    }
    
    @Override
    public WxaSession get(String sessionId) {
        return cache.get(SESSION_PREFIX.concat(sessionId));
    }

    @Override
    public void save(WxaSession session) {
        cache.setex(SESSION_PREFIX.concat(session.id), TIME_OUT * 60, session);
    }

    @Override
    public void update(WxaSession session) {
        save(session);
    }

}
