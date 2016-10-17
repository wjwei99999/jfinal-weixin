package com.jfinal.weixin.sdk.api.component.authorizer.type.api.result;

import com.jfinal.weixin.sdk.api.component.authorizer.type.ServiceType;
import com.jfinal.weixin.sdk.api.component.authorizer.type.VerifyType;

public class AuthInfo {

    private String       nick_name;
    private String       head_img;
    private TypeInfo     service_type_info;
    private TypeInfo     verify_type_info;
    private String       user_name;
    private String       alias;
    private BusinessInfo business_info;
    private String       qrcode_url;

    public ServiceType toServiceType() {
        return ServiceType.of(service_type_info.getId());
    }

    public VerifyType toVerifyType() {
        return VerifyType.of(verify_type_info.getId());
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public TypeInfo getService_type_info() {
        return service_type_info;
    }

    public void setService_type_info(TypeInfo service_type_info) {
        this.service_type_info = service_type_info;
    }

    public TypeInfo getVerify_type_info() {
        return verify_type_info;
    }

    public void setVerify_type_info(TypeInfo verify_type_info) {
        this.verify_type_info = verify_type_info;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public BusinessInfo getBusiness_info() {
        return business_info;
    }

    public void setBusiness_info(BusinessInfo business_info) {
        this.business_info = business_info;
    }

    public String getQrcode_url() {
        return qrcode_url;
    }

    public void setQrcode_url(String qrcode_url) {
        this.qrcode_url = qrcode_url;
    }
}