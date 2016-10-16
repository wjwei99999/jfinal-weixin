package com.jfinal.weixin.sdk.api.component;

/**
 * <p>
 * Created by Shylock on 25/09/2016.
 */
public class DefaultAuthorizedApi implements AuthorizedApi {
    public boolean isAuthorized(String appId) {
        return false;
    }

    @Override
    public ComponentAuthorizerAccessToken of(String appId) {
        return null;
    }
}
