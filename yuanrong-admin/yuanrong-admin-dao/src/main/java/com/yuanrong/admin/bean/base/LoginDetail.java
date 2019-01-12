package com.yuanrong.admin.bean.base;

import java.io.Serializable;

import com.yuanrong.admin.Enum.EnumLoginMode;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.bean.usermanagement.RegisteredUserInfo;
import com.yuanrong.admin.util.EnumUtil;

/**
 * 登录日志的实体类
 *
 * @author MDA
 */
public class LoginDetail extends BaseBean implements Serializable {
    // Fields
    private static final long serialVersionUID = 1L;

    /**
     * 主键，自增类型
     */
    private int loginDetailId;
    /*****自定义属性区域begin******/


    /**
     * 登录用户ID
     * 登录用户ID
     */
    private int registeredUserInfoId;

    /**
     * 登录用户
     * 登录用户
     */
    private RegisteredUserInfo registeredUserInfo;

    /**
     * 登录IP
     * 登录IP
     */
    private String ip;

    /**
     * 登录方式
     * 登录方式
     */
    private EnumLoginMode loginMode;
    /*
     * 用于接收前台数据
     */
    private Integer loginModeIndex;

    /**
     * 用户浏览器信息
     * 用户浏览器信息
     */
    private String userAgent;

    public LoginDetail(){}

    public LoginDetail(int registeredUserInfoId, String ip, EnumLoginMode loginMode,  String userAgent) {
        this.registeredUserInfoId = registeredUserInfoId;
        this.ip = ip;
        this.loginMode = loginMode;
        this.loginModeIndex = loginMode.getIndex();
        this.userAgent = userAgent;
    }

    public int getLoginDetailId() {
        return this.loginDetailId;
    }

    public void setLoginDetailId(int loginDetailId) {
        this.loginDetailId = loginDetailId;
    }

    /*****自定义属性区域begin.get/set******/

    public int getRegisteredUserInfoId() {
        return this.registeredUserInfoId;
    }

    public void setRegisteredUserInfoId(int registeredUserInfoId) {
        this.registeredUserInfoId = registeredUserInfoId;
    }

    public RegisteredUserInfo getRegisteredUserInfo() {
        return this.registeredUserInfo;
    }

    public void setRegisteredUserInfo(RegisteredUserInfo registeredUserInfo) {
        this.registeredUserInfo = registeredUserInfo;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public EnumLoginMode getLoginMode() {
        return loginMode;
    }

    public void setLoginMode(EnumLoginMode loginMode) {
        this.loginMode = loginMode;
        this.loginModeIndex = loginMode.getIndex();
    }

    public Integer getLoginModeIndex() {
        return loginModeIndex;
    }

    public void setLoginModeIndex(Integer loginModeIndex) {
        this.loginMode = (EnumLoginMode) EnumUtil.valueOf(EnumLoginMode.class, loginModeIndex);
        this.loginModeIndex = loginModeIndex;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
