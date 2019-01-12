package com.yuanrong.admin.bean.base;

import com.yuanrong.admin.bean.BaseBean;

import java.io.Serializable;
import java.util.Date;

/**
 * 验证码短信息类
 */
public class SMSValidateCode extends BaseBean implements Serializable {
    /**
     * 手机号
      */
    private String mobile;
    /**
     * 验证码
     */
    private String smsCode;
    /**
     * 当前时间
     */
    private Date  date;

    public SMSValidateCode() {
    }

    public SMSValidateCode(String mobile, String smsCode, Date date) {
        this.mobile = mobile;
        this.smsCode = smsCode;
        this.date = date;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
