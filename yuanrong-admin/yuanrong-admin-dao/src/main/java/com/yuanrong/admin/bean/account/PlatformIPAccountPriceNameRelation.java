package com.yuanrong.admin.bean.account;

import com.yuanrong.admin.bean.BaseBean;

import java.io.Serializable;

/**
 * Created by zhonghang on 2018/4/27.
 * 平台价格关系关系表
 */
public class PlatformIPAccountPriceNameRelation extends BaseBean implements Serializable {
    private int id;
    private int platformPriceID ; //	价格名称ID
    private  int shortVideoPlatformInfoID ; //	平台ID

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlatformPriceID() {
        return platformPriceID;
    }

    public void setPlatformPriceID(int platformPriceID) {
        this.platformPriceID = platformPriceID;
    }

    public int getShortVideoPlatformInfoID() {
        return shortVideoPlatformInfoID;
    }

    public void setShortVideoPlatformInfoID(int shortVideoPlatformInfoID) {
        this.shortVideoPlatformInfoID = shortVideoPlatformInfoID;
    }
}
