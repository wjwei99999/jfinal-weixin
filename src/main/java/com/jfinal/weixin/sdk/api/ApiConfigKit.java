package com.jfinal.weixin.sdk.api;

import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.weixin.sdk.cache.DefaultAccessTokenCache;
import com.jfinal.weixin.sdk.cache.IAccessTokenCache;
import com.jfinal.weixin.sdk.session.DefaultWxSessionManager;
import com.jfinal.weixin.sdk.session.WxSessionManager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 将 ApiConfig 绑定到 ThreadLocal 的工具类，以方便在当前线程的各个地方获取 ApiConfig 对象：
 * 1：如果控制器继承自 MsgController 该过程是自动的，详细可查看 MsgInterceptor 与之的配合
 * 2：如果控制器继承自 ApiController 该过程是自动的，详细可查看 ApiInterceptor 与之的配合
 * 3：如果控制器没有继承自 MsgController、ApiController，则需要先手动调用
 * ApiConfigKit.setThreadLocalAppId(appId) 来绑定 appId 到线程之上
 */
public class ApiConfigKit {
    private static final Log                    log                               = Log.getLog(
        ApiConfigKit.class);
    private static final ThreadLocal<ApiConfig> API_CONFIG_THREAD_LOCAL           = new ThreadLocal<ApiConfig>();
    private static final ThreadLocal<ApiConfig> COMPONENT_API_CONFIG_THREAD_LOCAL = new ThreadLocal<ApiConfig>();
    private static final ThreadLocal<String>    TL                                = new ThreadLocal<String>();
    private static final ThreadLocal<String>    TL_AUTHORIZER_APPID               = new ThreadLocal<String>(); // 存放授权方 AppId
    private static final Map<String, ApiConfig> CFG_MAP                           = new ConcurrentHashMap<String, ApiConfig>();
    private static final String                 DEFAULT_CFG_KEY                   = "_default_cfg_key_";
    private static       IAccessTokenCache      accessTokenCache                  = getAccessTokenCache();
    // 开发模式将输出消息交互 xml 到控制台
    private static       boolean                devMode                           = false;
    /**
     * 公众号第三方平台模式，默认 flase
     */
    private static       boolean				componentMode					  = false;
    /**
     * 是否启用session，默认不启用
     */
    private static       WxSessionManager       sessionManager                    = null;

    public static boolean isDevMode() {
        return devMode;
    }

    public static void setDevMode(boolean devMode) {
        ApiConfigKit.devMode = devMode;
    }

    /**
     * 获取是否为公众号第三方平台模式
     * @return
     */
    public static  boolean isComponentMode(){
    	return componentMode;
    }
    
    /**
     * 设置公众号第三方平台模式
     * @param componentMode
     */
    public static void setComponentMode(boolean componentMode){
    	ApiConfigKit.componentMode = componentMode;
    }
    
    /**
     * 添加公众号配置，每个appId只需添加一次，相同appId将被覆盖。
     * 第一个添加的将作为默认公众号配置
     *
     * @param apiConfig 公众号配置
     * @return ApiConfig 公众号配置
     */
    public static ApiConfig putApiConfig(ApiConfig apiConfig) {
        if (CFG_MAP.size() == 0) {
            CFG_MAP.put(DEFAULT_CFG_KEY, apiConfig);
        }
        return CFG_MAP.put(apiConfig.getAppId(), apiConfig);
    }

    public static ApiConfig removeApiConfig(ApiConfig apiConfig) {
        return removeApiConfig(apiConfig.getAppId());
    }

    public static ApiConfig removeApiConfig(String appId) {
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

    /**
     * 在公众号第三方模式（componentMode=true）下，该属性存储的是第三方平台 appId
     * @return
     */
    public static String getAppId() {
        String appId = TL.get();
        if (StrKit.isBlank(appId)) {
            appId = CFG_MAP.get(DEFAULT_CFG_KEY).getAppId();
        }
        return appId;
    }

    public static ApiConfig getApiConfig() {
        String appId = getAppId();
        return getApiConfig(appId);
    }

    public static ApiConfig getApiConfig(String appId) {
        log.debug("appId: " + appId);
        ApiConfig cfg = CFG_MAP.get(appId);
        if (cfg == null)
            throw new IllegalStateException(
                "需事先调用 ApiConfigKit.putApiConfig(apiConfig) 将 appId对应的 ApiConfig 对象存入，" +
                "如JFinalConfig.afterJFinalStart()中调用, 才可以使用 ApiConfigKit.getApiConfig() 系列方法");
        return cfg;
    }

    public static IAccessTokenCache getAccessTokenCache() {
        return ApiConfigKit.accessTokenCache;
    }

    public static void setAccessTokenCache(IAccessTokenCache accessTokenCache) {
        ApiConfigKit.accessTokenCache = accessTokenCache;
    }

    /**
     * 启用默认的session管理器
     */
    public static void enableDefaultWxSessionManager() {
        setWxSessionManager(new DefaultWxSessionManager());
    }

    /**
     * 获取微信session管理器
     *
     * @return sessionManager session管理器
     */
    public static WxSessionManager getWxSessionManager() {
        WxSessionManager sessionManager = ApiConfigKit.sessionManager;
        if (null == sessionManager) {
            throw new NullPointerException(
                "WxSessionManager is null, Please setWxSessionManager first！");
        }
        return sessionManager;
    }

    /**
     * 设置微信session管理器
     *
     * @param sessionManager session管理器
     */
    public static void setWxSessionManager(WxSessionManager sessionManager) {
        ApiConfigKit.sessionManager = sessionManager;
    }

    public static void setThreadLocalComponentApiConfig(ApiConfig apiConfig) {
        COMPONENT_API_CONFIG_THREAD_LOCAL.set(apiConfig);
    }

    public static void removeThreadLocalComponentApiConfig() {
        COMPONENT_API_CONFIG_THREAD_LOCAL.remove();
    }

    public static ApiConfig getComponentApiConfig() {
        ApiConfig result = COMPONENT_API_CONFIG_THREAD_LOCAL.get();
        if (result == null)
            throw new IllegalStateException(
                "需要事先使用 ApiConfigKit.setThreadLocalApiConfig(apiConfig) 将 ApiConfig对象存入，才可以调用 ApiConfigKit.getComponentApiConfig() 方法");
        return result;
    }
    
    public static void setThreadLocalAuthorizerAppId(String authorizerAppId) {
        TL_AUTHORIZER_APPID.set(authorizerAppId);
    }

    public static void removeThreadLocalAuthorizerAppId() {
    	TL_AUTHORIZER_APPID.remove();
    }

    public static String getThreadLocalAuthorizerAppId() {
        String appId = TL_AUTHORIZER_APPID.get();
        if (appId == null)
            throw new IllegalStateException(
                "需要事先使用  ApiConfigKit.setThreadLocalAuthorizerAppId(String authorizerAppId) 将 authorizerAppId 存入，才可以调用  ApiConfigKit.getThreadLocalAuthorizerAppId() 方法");
        return appId;
    }
}
