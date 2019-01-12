package com.yuanrong.admin.bean.base;

import com.yuanrong.admin.bean.BaseBean;

import java.io.Serializable;

/**
 * Created by zhonghang on 2018/4/28.
 * 城市
 */
public class AreaCity extends BaseBean implements Serializable {
    /**
     * 主键
     */
    private String adcd;
    /**
     * 城市名
     */
    private String adnm;
    /**
     * 父级
     */
    private String padcd;
    private char maintenance;

    public String getAdcd() {
        return adcd;
    }

    public void setAdcd(String adcd) {
        this.adcd = adcd;
    }

    public String getAdnm() {
        return adnm;
    }

    public void setAdnm(String adnm) {
        this.adnm = adnm;
    }

    public String getPadcd() {
        return padcd;
    }

    public void setPadcd(String padcd) {
        this.padcd = padcd;
    }

    public char getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(char maintenance) {
        this.maintenance = maintenance;
    }
}
