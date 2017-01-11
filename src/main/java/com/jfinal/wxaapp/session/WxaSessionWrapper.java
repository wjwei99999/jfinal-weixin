package com.jfinal.wxaapp.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import com.jfinal.kit.StrKit;
import com.jfinal.wxaapp.WxaConfigKit;

/**
 * 默认的Session管理器
 * @author L.cm
 */
class WxaSessionWrapper extends HttpServletRequestWrapper {
    // session管理器
    private static WxaSessionManager sessionManager = WxaConfigKit.getSessionManager();
    // sessionId，由小程序请求传递
    private final String wxaSessionId;
    
    public WxaSessionWrapper(HttpServletRequest request, String wxaSessionId) {
        super(request);
        this.wxaSessionId = wxaSessionId;
    }

    @Override
    public String getRequestedSessionId() {
        return wxaSessionId;
    }

    @Override
    public HttpSession getSession(boolean create) {
        if (create) {
            return this.getSession();
        }
        WxaSession session = sessionManager.get(wxaSessionId);
        if (null != session) {
            // 由于sessionManager不参与序列化，加上序列化的问题，故手动设置
            session.setManager(sessionManager);
        }
        return session;
    }

    @Override
    public HttpSession getSession() {
        WxaSession session = sessionManager.get(wxaSessionId);
        if (null == session) {
            session = new WxaSession(wxaSessionId);
            sessionManager.save(session);
        }
        // 由于sessionManager不参与序列化，加上序列化的问题，故手动设置
        session.setManager(sessionManager);
        return session;
    }

    @Override
    public boolean isRequestedSessionIdValid() {
        return StrKit.notBlank(wxaSessionId);
    }

}
