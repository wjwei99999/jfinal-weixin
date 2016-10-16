package com.jfinal.weixin.sdk.api.component.authorizer.type;

/**
 * open_store:是否开通微信门店功能
 * open_scan:是否开通微信扫商品功能
 * open_pay:是否开通微信支付功能
 * open_card:是否开通微信卡券功能
 * open_shake:是否开通微信摇一摇功能
 * <p>
 * Created by Shylock on 16/9/25.
 */
public enum BusinessType {
    Store(0, "微信门店"), Scan(1, "微信扫商品"), Pay(2, "微信支付"), Card(3, "微信卡券"), Shake(4, "微信摇一摇");

    private int    code;
    private String memo;

    BusinessType(int code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public int getCode() {
        return code;
    }

    public String getMemo() {
        return memo;
    }

    public static BusinessType of(int code) {
        for (BusinessType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        return null;
    }
}
