package com.yuanrong.admin.bean.base;

import com.yuanrong.common.util.MD5Util;

import java.io.Serializable;

/**
 * 记录前台登录用户信息
 */
public class UserInfo implements Serializable{
    private static final long serialVersionUID = 5351919772485454770L;

    /**
     * 主键
     */
    private Integer recID;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 手机号
     */
    private String mobile;

    /**
     * 用户I
     */
    private String ip;
    /**
     * 用户浏览器信息
     */
    private String userAgent;

    /**
     * 用户 密码
     */
    private String password;

    public UserInfo() {
    }

    public UserInfo(Integer recID, String userName, String mobile, String password) {
        this.recID = recID;
        this.userName = userName;
        this.mobile = mobile;
        this.password = password;
    }

    public UserInfo(Integer recID, String userName, String mobile) {
        this.recID = recID;
        this.userName = userName;
        this.mobile = mobile;
    }

    public UserInfo(Integer recID, String userName, String mobile, String ip, String userAgent, String password) {
        this.recID = recID;
        this.userName = userName;
        this.mobile = mobile;
        this.ip = ip;
        this.userAgent = userAgent;
        this.password = password;
    }

    public Integer getRecID() {
        return recID;
    }

    public void setRecID(Integer recID) {
        this.recID = recID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken(long time){
        return MD5Util.md5(MD5Util.md5(this.getMobile()+this.getRecID()+this.getIp()+this.getUserAgent()+time)+this.getPassword());
    }
}
