package com.jfinal.weixin.sdk.api.component.authorizer.type;

/**
 * 授权方认证类型，-1代表未认证，
 * 0代表微信认证，
 * 1代表新浪微博认证，
 * 2代表腾讯微博认证，
 * 3代表已资质认证通过但还未通过名称认证，
 * 4代表已资质认证通过、还未通过名称认证，但通过了新浪微博认证，
 * 5代表已资质认证通过、还未通过名称认证，但通过了腾讯微博认证
 * <p>
 * Created by Shylock on 16/9/25.
 */
public enum VerifyType {
    NONE(-1, "未认证"),
    WECHAT(0, "微信"),
    SINA_WEIBO(1, "新浪微博"),
    TENCENT_WEIBO(2, "腾讯微博"),
    QC_NO_NAME(3, "资质认证通过但还未通过名称认证"),
    QC_SIAN_WEIBO_NO_NAME(4, "资质认证通过、还未通过名称认证，但通过了新浪微博认证"),
    QC_TENCENT_WEIBO_NO_NAME(5, "资质认证通过、还未通过名称认证，但通过了腾讯微博认证");

    private int    code;
    private String memo;

    VerifyType(int code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public int getCode() {
        return code;
    }

    public String getMemo() {
        return memo;
    }

    public static VerifyType of(int code) {
        for (VerifyType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        return null;
    }
}
