package com.jfinal.weixin.sdk.api.component.authorizer.type;

/**
 * 授权方公众号类型，0代表订阅号，1代表由历史老帐号升级后的订阅号，2代表服务号
 * <p>
 * Created by Shylock on 16/9/25.
 */

public enum ServiceType {
    Subscription(0, "订阅号"), OldToSubscription(1, "订阅号"), Service(2, "服务号");

    private int    code;
    private String memo;

    ServiceType(int code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public int getCode() {
        return code;
    }

    public String getMemo() {
        return memo;
    }

    public static ServiceType of(int code) {
        for (ServiceType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        return null;
    }
}
