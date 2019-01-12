package com.yuanrong.admin.bean.account;

import com.yuanrong.admin.bean.BaseBean;

import java.io.Serializable;

/**
 * Created by zhonghang on 2018/6/4.
 */
public class PlatformPriceName extends BaseBean implements Serializable {
    private int id;
    /**
     * 报价名称
     */
    private String priceName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPriceName() {
        return priceName;
    }

    public void setPriceName(String priceName) {
        this.priceName = priceName;
    }
}
