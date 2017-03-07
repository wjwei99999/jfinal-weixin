package com.jfinal.wxaapp;

import com.jfinal.wxaapp.msg.IMsgParser;
import com.jfinal.wxaapp.msg.JsonMsgParser;
import com.jfinal.wxaapp.msg.XmlMsgParser;

/**
 * 小程序配置工具
 * @author L.cm
 *
 */
public class WxaConfigKit {
    /**
     * 小程序消息解析
     */
    private static IMsgParser msgParser = new XmlMsgParser();
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
    public static void useJsonMsgParser() {
        WxaConfigKit.msgParser = new JsonMsgParser();
    }

    // 开发模式将输出消息交互 xml、json 到控制台
    private static boolean devMode = false;
    
    public static void setDevMode(boolean devMode) {
        WxaConfigKit.devMode = devMode;
    }
    
    public static boolean isDevMode() {
        return devMode;
    }
    
    public static WxaConfig getWxaConfig() {
//    	AppID(小程序ID) wx4f53594f9a6b3dcb
//    	AppSecret(小程序密钥) eec6482ba3804df05bd10895bace0579
        WxaConfig wc = new WxaConfig();
        wc.setAppId("wx4f53594f9a6b3dcb");
        wc.setAppSecret("eec6482ba3804df05bd10895bace0579");
        return wc;
    }
}
