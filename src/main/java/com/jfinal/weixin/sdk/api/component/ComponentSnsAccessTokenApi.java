/**
 * Copyright ® 2016 DQ ENCH Co. Ltd.
 * All right reserved.
 */

package com.jfinal.weixin.sdk.api.component;

import com.jfinal.kit.StrKit;
import com.jfinal.weixin.sdk.kit.ParaMap;
import com.jfinal.weixin.sdk.kit.PaymentKit;
import com.jfinal.weixin.sdk.utils.HttpUtils;
import com.jfinal.weixin.sdk.utils.RetryUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 代公众号发起网页授权获取 API
 */
public class ComponentSnsAccessTokenApi
{
    private static String url = "https://api.weixin.qq.com/sns/oauth2/component/access_token?grant_type=authorization_code";
    private static String authorize_uri = "https://open.weixin.qq.com/connect/oauth2/authorize";
    private static String qrconnect_url = "https://open.weixin.qq.com/connect/qrconnect";

    /**
     * 生成Authorize链接
     * @param authorizerAppId 授权方公众号appid
     * @param redirect_uri 重定向地址，需要urlencode，这里填写的应是服务开发方的回调地址
     * @param componentAppId 服务方的appid，在申请创建公众号服务成功后，可在公众号服务详情页找到
     * @param snsapiBase snsapi_base（不弹出授权页面，只能拿到用户openid）snsapi_userinfo（弹出授权页面，这个可以通过 openid 拿到昵称、性别、所在地）
     * @return url
     */
    public static String getAuthorizeURL(String authorizerAppId, String redirect_uri, String componentAppId, boolean snsapiBase) {
        return getAuthorizeURL(authorizerAppId, redirect_uri, null, componentAppId, snsapiBase);
    }

    /**
     * 生成Authorize链接
     * @param authorizerAppId 授权方公众号appid
     * @param redirectUri 重定向地址，需要urlencode，这里填写的应是服务开发方的回调地址
     * @param state 重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     * @param componentAppid 服务方的appid，在申请创建公众号服务成功后，可在公众号服务详情页找到
     * @param snsapiBase snsapi_base（不弹出授权页面，只能拿到用户openid）snsapi_userinfo（弹出授权页面，这个可以通过 openid 拿到昵称、性别、所在地）
     * @return url
     */
    public static String getAuthorizeURL(String authorizerAppId, String redirectUri, String state,String componentAppId, boolean snsapiBase) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", authorizerAppId);
        params.put("response_type", "code");
        params.put("redirect_uri", redirectUri);
        // snsapi_base（不弹出授权页面，只能拿到用户openid）
        // snsapi_userinfo（弹出授权页面，这个可以通过 openid 拿到昵称、性别、所在地）
        if (snsapiBase) {
            params.put("scope", "snsapi_base");
        } else {
            params.put("scope", "snsapi_userinfo");
        }
        if (StrKit.isBlank(state)) {
            params.put("state", "wx#wechat_redirect");
        } else {
            params.put("state", state.concat("#wechat_redirect"));
        }
        params.put("component_appid",componentAppId);
        
        String para = PaymentKit.packageSign(params, false);
        return authorize_uri + "?" + para;
    }


    /**
     * 生成网页二维码授权链接 TODO 待验证正确性
     * @param appId 应用id
     * @param redirect_uri 回跳地址
     * @return url
     */
    public static String getQrConnectURL(String appId, String redirect_uri) {
        return getQrConnectURL(appId, redirect_uri, null);
    }

    /**
     * 生成网页二维码授权链接  TODO 待验证正确性
     * @param appId 应用id
     * @param redirect_uri 回跳地址
     * @param state 重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     * @return url
     */
    public static String getQrConnectURL(String appId, String redirect_uri, String state) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", appId);
        params.put("response_type", "code");
        params.put("redirect_uri", redirect_uri);
        params.put("scope", "snsapi_login");
        if (StrKit.isBlank(state)) {
            params.put("state", "wx#wechat_redirect");
        } else {
            params.put("state", state.concat("#wechat_redirect"));
        }
        String para = PaymentKit.packageSign(params, false);
        return qrconnect_url + "?" + para;
    }

    /**
     * 通过code获取access_token
     *
     * @param appId  应用唯一标识
     * @param code   第一步获取的code参数
     * @param grantType 填authorization_code
     * @param componentAppid 服务开发方的appid
     * @param componentAccessToken 服务开发方的access_token
     * @return ComponentSnsAccessToken
     */
    public static ComponentSnsAccessToken getSnsAccessToken(String appId, String code, String componentAppid, String componentAccessToken)
    {
        final Map<String, String> queryParas = ParaMap.create("appid", appId).put("code", code).put("component_appid", componentAppid).put("component_access_token", componentAccessToken).getData();

        return RetryUtils.retryOnException(3, new Callable<ComponentSnsAccessToken>() {

            @Override
            public ComponentSnsAccessToken call() throws Exception {
                String json = HttpUtils.get(url, queryParas);
                return new ComponentSnsAccessToken(json);
            }
        });
    }
}
