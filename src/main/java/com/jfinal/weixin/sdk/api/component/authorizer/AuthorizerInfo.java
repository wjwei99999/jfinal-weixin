/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api.component.authorizer;

import com.jfinal.weixin.sdk.api.component.authorizer.type.BusinessType;
import com.jfinal.weixin.sdk.api.component.authorizer.type.ServiceType;
import com.jfinal.weixin.sdk.api.component.authorizer.type.VerifyType;
import com.jfinal.weixin.sdk.api.component.authorizer.type.api.result.AuthInfo;
import com.jfinal.weixin.sdk.utils.JsonUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 封装 access_token
 */
public class AuthorizerInfo implements Serializable {


    private static final long serialVersionUID = -877395267037045760L;
    private String             nickName;
    private String             headImg;
    private ServiceType        serviceType;
    private VerifyType         verifyType;
    private String             userName;
    /**
     * 用以了解以下功能的开通状况（0代表未开通，1代表已开通）：
     * open_store:是否开通微信门店功能
     * open_scan:是否开通微信扫商品功能
     * open_pay:是否开通微信支付功能
     * open_card:是否开通微信卡券功能
     * open_shake:是否开通微信摇一摇功能
     */
    private List<BusinessType> openedBusiness;
    private String             alias;
    private String             qrCodeUrl;

    private String json;

    public AuthorizerInfo(String jsonStr) {
        this.json = jsonStr;
        try {
            AuthInfo temp = JsonUtils.parse(jsonStr, AuthInfo.class);
            this.alias = temp.getAlias();
            this.nickName = temp.getNick_name();
            this.headImg = temp.getHead_img();
            this.serviceType = temp.toServiceType();
            this.verifyType = temp.toVerifyType();
            this.userName = temp.getUser_name();
            this.openedBusiness = temp.getBusiness_info().to();
            this.qrCodeUrl = temp.getQrcode_url();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getNickName() {
        return nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public VerifyType getVerifyType() {
        return verifyType;
    }

    public String getUserName() {
        return userName;
    }

    public List<BusinessType> getOpenedBusiness() {
        return openedBusiness;
    }

    public String getAlias() {
        return alias;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }


}
