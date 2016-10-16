package com.jfinal.weixin.sdk.api.component;

/**
 * <p>
 * Created by Shylock on 25/09/2016.
 */
public interface AuthorizedApi {
    boolean isAuthorized(String appId);

    ComponentAuthorizerAccessToken of(String appId);
}
