package com.jfinal.weixin.sdk.api.component.authorizer.type;

/**
 * <p>
 * Created by Shylock on 16/9/25.
 */

/**
 * 公众号授权给开发者的权限集列表，ID为1到15时分别代表：
 * 消息管理权限
 * 用户管理权限
 * 帐号服务权限
 * 网页服务权限
 * 微信小店权限
 * 微信多客服权限
 * 群发与通知权限
 * 微信卡券权限
 * 微信扫一扫权限
 * 微信连WIFI权限
 * 素材管理权限
 * 微信摇周边权限
 * 微信门店权限
 * 微信支付权限
 * 自定义菜单权限
 * <p>
 * 请注意：
 * 1）该字段的返回不会考虑公众号是否具备该权限集的权限（因为可能部分具备），请根据公众号的帐号类型和认证情况，来判断公众号的接口权限。
 */
public enum FunctionType {
    Message(1, "消息管理权限"),
    User(2, "用户管理权限"),
    AccountService(3, "帐号服务权限"),
    WebService(4, "网页服务权限"),
    WechatMall(5, "微信小店权限"),
    CustomService(6, "微信多客服权限"),
    TemplateMessage(7, "群发与通知权限"),
    WechatCard(8, "微信卡券权限"),
    WechatScan(9, "微信扫一扫权限"),
    WechatWiFi(10, "微信连WIFI权限"),
    Media(11, "素材管理权限"),
    WechatShake(12, "微信摇周边权限"),
    WechatStore(13, "微信门店权限"),
    WechatPay(14, "微信支付权限"),
    WechatMenu(15, "自定义菜单权限");
    private int    code;
    private String memo;

    FunctionType(int code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public int getCode() {
        return code;
    }

    public String getMemo() {
        return memo;
    }

    public static FunctionType of(int code) {
        for (FunctionType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        return null;
    }
}
