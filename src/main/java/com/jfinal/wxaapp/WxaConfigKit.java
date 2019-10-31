package com.jfinal.wxaapp;

import com.jfinal.kit.StrKit;
import com.jfinal.wxaapp.msg.IMsgParser;
import com.jfinal.wxaapp.msg.JsonMsgParser;
import com.jfinal.wxaapp.msg.XmlMsgParser;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 小程序配置工具
 * @author L.cm
 *
 * 将 WxaConfig 绑定到 ThreadLocal 的工具类，以方便在当前线程的各个地方获取 WxaConfig 对象：
 * 1：如果控制器继承自 WxaMsgController 该过程是自动的，详细可查看 WxaMsgInterceptor 与之的配合
 * 2：如果控制器继承自 WxaController 该过程是自动的，详细可查看 WxaInterceptor 与之的配合
 * 3：如果控制器没有继承自 WxaMsgController、WxaController，则需要先手动调用
 *    WxaConfigKit.setThreadLocalAppId(appId) 来绑定 appId 到线程之上
 */
public class WxaConfigKit {
    private static final ThreadLocal<String> TL = new ThreadLocal<>();
    private static final Map<String, WxaConfig> CFG_MAP = new ConcurrentHashMap<>();
    private static final String DEFAULT_CFG_KEY = "_default_cfg_key_";
    static final XmlMsgParser MSG_PARSER_XML = new XmlMsgParser();
    static final JsonMsgParser MSG_PARSER_JSON = new JsonMsgParser();

    /**
     * 小程序消息解析
     */
    private static IMsgParser msgParser = MSG_PARSER_XML;

    /**
     * 获取当前线程的小程序消息解析器
     *
     * @return {IMsgParser}
     */
    public static IMsgParser getMsgParser() {
        IMsgParser type = getWxaConfig().getMsgParser();
        return null != type ? type : WxaConfigKit.msgParser;
    }

    /**
     * 设置全局小程序消息解析器默认值为JSON
     */
    public static void useJsonMsgParser() {
        WxaConfigKit.msgParser = MSG_PARSER_JSON;
    }

    // 开发模式将输出消息交互 xml、json 到控制台
    private static boolean devMode = false;

    public static void setDevMode(boolean devMode) {
        WxaConfigKit.devMode = devMode;
    }

    public static boolean isDevMode() {
        return devMode;
    }

    /**
     * 添加小程序号配置，每个appId只需添加一次，相同appId将被覆盖。
     * 第一个添加的将作为默认小程序配置
     *
     * @param wxaConfig 小程序号配置
     * @return
     */
    public static WxaConfig putWxaConfig(WxaConfig wxaConfig) {
        if (CFG_MAP.size() == 0) {
            CFG_MAP.put(DEFAULT_CFG_KEY, wxaConfig);
        }
        return CFG_MAP.put(wxaConfig.getAppId(), wxaConfig);
    }

    public static void setWxaConfig(WxaConfig wxaConfig) {
        putWxaConfig(wxaConfig);
    }

    public static String getAppId() {
        String appId = TL.get();
        if (StrKit.isBlank(appId)) {
            appId = CFG_MAP.get(DEFAULT_CFG_KEY).getAppId();
        }
        return appId;
    }

    public static WxaConfig getWxaConfig() {
        String appId = getAppId();
        return getWxaConfig(appId);
    }

    public static WxaConfig getWxaConfig(String appId) {
        WxaConfig cfg = CFG_MAP.get(appId);
        if (cfg == null)
            throw new IllegalStateException("需事先调用 WxaConfigKit.putWxaConfig(wxaConfig) " +
                "将 appId对应的 WxaConfig 对象存入，如JFinalConfig.onStart()中调用," +
                " 才可以使用 WxaConfigKit.getWxaConfig() 系列方法");
        return cfg;
    }


    public static WxaConfig removeApiConfig(WxaConfig wxaConfig) {
        return removeApiConfig(wxaConfig.getAppId());
    }

    public static WxaConfig removeApiConfig(String appId) {
        return CFG_MAP.remove(appId);
    }

    public static void setThreadLocalAppId(String appId) {
        if (StrKit.isBlank(appId)) {
            appId = CFG_MAP.get(DEFAULT_CFG_KEY).getAppId();
        }
        TL.set(appId);
    }

    public static void removeThreadLocalAppId() {
        TL.remove();
    }
}
