package com.yuanrong.admin.bean.account;

import com.yuanrong.admin.bean.BaseBean;

import java.io.Serializable;

/**
 * Created by zhonghang on 2018/7/4.
 * 账号唯一标识
 */
public class Account  extends BaseBean implements Serializable {
    private int accountId;
    /**
     * 唯一平台唯一标识
     */
    private String pid;
    /**
     * 平台ID
     */
    private int shortVideoPlatformInfoId;

    public Account() {
    }

    public Account(String pid, int shortVideoPlatformInfoId) {
        this.pid = pid;
        this.shortVideoPlatformInfoId = shortVideoPlatformInfoId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getShortVideoPlatformInfoId() {
        return shortVideoPlatformInfoId;
    }

    public void setShortVideoPlatformInfoId(int shortVideoPlatformInfoId) {
        this.shortVideoPlatformInfoId = shortVideoPlatformInfoId;
    }
}
