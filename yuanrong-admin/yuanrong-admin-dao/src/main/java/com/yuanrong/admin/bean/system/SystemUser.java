package com.yuanrong.admin.bean.system;


import com.yuanrong.admin.Enum.EnumYesOrNo;
import com.yuanrong.admin.bean.BaseBean;

import java.io.Serializable;

/**
 * Created by zhonghang on 2018/4/11.
 */
public class SystemUser extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键，自增类型
     */
    private int systemUserId;
    /**
     * 用户名 用户名
     */
    private String userName;

    /**
     * 密码 密码
     */
    private String password;

    /**
     * 名称
     */
    private String realName;

    /**
     * 是否可登录
     */
    private EnumYesOrNo isLogin;

    private String isLoginIndex;

    public int getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(int systemUserId) {
        this.systemUserId = systemUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public EnumYesOrNo getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(EnumYesOrNo isLogin) {
        this.isLogin = isLogin;
    }

    public String getIsLoginIndex() {
        return isLoginIndex;
    }

    public void setIsLoginIndex(String isLoginIndex) {
        this.isLoginIndex = isLoginIndex;
    }
}
