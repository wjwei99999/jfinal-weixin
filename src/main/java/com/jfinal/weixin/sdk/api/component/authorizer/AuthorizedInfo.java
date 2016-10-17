package com.jfinal.weixin.sdk.api.component.authorizer;

import com.jfinal.weixin.sdk.utils.JsonUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * Created by Shylock on 16/9/25.
 */
public class AuthorizedInfo implements Serializable {

    private static final long serialVersionUID = -1633011062366786962L;


    private AuthorizerInfo authorizerInfo;

    private AuthorizationInfo authorizationInfo;
    private String            json;

    public AuthorizedInfo() {
    }

    public AuthorizedInfo(String jsonStr) {
        this.json = jsonStr;
        try {
            Map<String, Object> temp            = JsonUtils.parse(jsonStr, Map.class);
            final Object        authorizer_info = temp.get("authorizer_info");
            if (authorizer_info != null) {
                authorizerInfo = new AuthorizerInfo(JsonUtils.toJson(authorizer_info));
            }
            final Object authorization_info = temp.get("authorization_info");
            if (authorization_info != null) {
                authorizationInfo = new AuthorizationInfo(JsonUtils.toJson(authorization_info));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getJson() {
        return json;
    }

    public AuthorizerInfo getAuthorizerInfo() {
        return authorizerInfo;
    }

    public AuthorizationInfo getAuthorizationInfo() {
        return authorizationInfo;
    }
}
