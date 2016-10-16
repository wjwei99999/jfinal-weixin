package com.jfinal.weixin.sdk.api.component.authorizer.type.api.result;

import com.jfinal.weixin.sdk.api.component.authorizer.type.BusinessType;

import java.util.ArrayList;
import java.util.List;

public class BusinessInfo {
    private static final int OPENED = 1;
    private Integer open_store;
    private Integer open_scan;
    private Integer open_pay;
    private Integer open_card;
    private Integer open_shake;

    public Integer getOpen_store() {
        return open_store;
    }

    public void setOpen_store(Integer open_store) {
        this.open_store = open_store;
    }

    public Integer getOpen_scan() {
        return open_scan;
    }

    public void setOpen_scan(Integer open_scan) {
        this.open_scan = open_scan;
    }

    public Integer getOpen_pay() {
        return open_pay;
    }

    public void setOpen_pay(Integer open_pay) {
        this.open_pay = open_pay;
    }

    public Integer getOpen_card() {
        return open_card;
    }

    public void setOpen_card(Integer open_card) {
        this.open_card = open_card;
    }

    public Integer getOpen_shake() {
        return open_shake;
    }

    public void setOpen_shake(Integer open_shake) {
        this.open_shake = open_shake;
    }

    public List<BusinessType> to() {
        List<BusinessType> types = new ArrayList<BusinessType>();
        if (open_store == OPENED) {
            types.add(BusinessType.Store);
        }
        if (open_scan == OPENED) {
            types.add(BusinessType.Scan);
        }
        if (open_pay == OPENED) {
            types.add(BusinessType.Pay);
        }
        if (open_card == OPENED) {
            types.add(BusinessType.Card);
        }
        if (open_shake == OPENED) {
            types.add(BusinessType.Shake);
        }
        return types;
    }
}