/**
 * Copyright (c) 2011-2014, L.cm 卢春梦 (qq596392912@gmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.wxaapp.session;

import java.io.Serializable;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import com.jfinal.core.JFinal;

/**
 * WxaSession
 * 
 * 参考：org.apache.catalina.session.StandardSession
 * 
 * @author L.cm
 */
@SuppressWarnings({"rawtypes", "unchecked"})
class WxaSession implements HttpSession, Serializable {
    private static final long serialVersionUID = -719739616470614546L;
    
    protected String id = null;
    protected final Map<String, Object> attributes = new ConcurrentHashMap();
    protected final long creationTime = System.currentTimeMillis();
    protected volatile int maxInactiveInterval = -1;
    protected transient WxaSessionManager manager;
    
    public WxaSession(String id) {
        this.id = id;
    }

    @Override
    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    @Override
    public Enumeration getAttributeNames() {
        Set<String> names = new HashSet();
        names.addAll(this.attributes.keySet());
        return Collections.enumeration(names);
    }

    @Override
    public long getCreationTime() {
        return this.creationTime;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public long getLastAccessedTime() {
        return this.creationTime;
    }

    @Override
    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }
    
    @Override
    public ServletContext getServletContext() {
        return JFinal.me().getServletContext();
    }

    @Deprecated
    @Override
    public javax.servlet.http.HttpSessionContext getSessionContext() {
        return null;
    }

    @Deprecated
    @Override
    public Object getValue(String key) {
        return getAttribute(key);
    }

    @Deprecated
    @Override
    public String[] getValueNames() {
        return (String[]) this.attributes.keySet().toArray(new String[0]);
    }

    @Override
    public void invalidate() {}

    @Override
    public boolean isNew() {
        return false;
    }

    @Deprecated
    @Override
    public void putValue(String key, Object value) {
        setAttribute(key, value);
    }

    @Override
    public synchronized void removeAttribute(String key) {
        attributes.remove(key);
        manager.update(this);
    }

    @Deprecated
    @Override
    public void removeValue(String key) {
        removeAttribute(key);
    }

    @Override
    public synchronized void setAttribute(String key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException("setAttribute: name parameter cannot be null");
        }
        if (value == null) {
            removeAttribute(key);
            return;
        }
        attributes.put(key, value);
        manager.update(this);
    }

    @Override
    public void setMaxInactiveInterval(int interval) {
        this.maxInactiveInterval = interval;
    }

    public synchronized void setManager(WxaSessionManager manager) {
        this.manager = manager;
    }
}