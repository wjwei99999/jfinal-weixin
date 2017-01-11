package com.jfinal.wxaapp;

import com.jfinal.wxaapp.msg.IMsgParser;
import com.jfinal.wxaapp.msg.XmlMsgParser;
import com.jfinal.wxaapp.session.DefaultWxaSessionManager;
import com.jfinal.wxaapp.session.WxaSessionManager;

public class WxaConfigKit {
    private static final String DEFAULT_SESSION_ID_NAME = "wxa-sessionid";
    /**
     * sessionid传递时所用的名称
     */
    private static String sessionIdName = DEFAULT_SESSION_ID_NAME;
    /**
     * 默认使用内存管理session
     */
    private static WxaSessionManager sessionManager = new DefaultWxaSessionManager();
    /**
     * 小程序消息解析
     */
    private static IMsgParser msgParser = new XmlMsgParser();

    public static void setSessionIdName(String sessionIdName) {
        WxaConfigKit.sessionIdName = sessionIdName;
    }

    public static String getSessionIdName() {
        return sessionIdName;
    }
    
    /**
     * 设置微信session管理器
     * @param sessionManager session管理器
     */
    public static void setSessionManager(WxaSessionManager sessionManager) {
        WxaConfigKit.sessionManager = sessionManager;
    }
    
    /**
     * 获取微信session管理器
     * @return sessionManager session管理器
     */
    public static WxaSessionManager getSessionManager() {
        return WxaConfigKit.sessionManager;
    }

    /**
     * 获取小程序消息解析器
     * @return {IMsgParser}
     */
    public static IMsgParser getMsgParser() {
        return msgParser;
    }
    /**
     * 设置小程序消息解析器
     * @param msgParser
     */
    public static void setMsgParser(IMsgParser msgParser) {
        WxaConfigKit.msgParser = msgParser;
    }

    public static WxaConfig getWxaConfig() {
        WxaConfig wc = new WxaConfig();
        wc.setAppId("wx9114b997bd86f8ed");
        wc.setAppSecret("d27551c7803cf16015e536b192d5d03b");
        return wc;
    }
}
