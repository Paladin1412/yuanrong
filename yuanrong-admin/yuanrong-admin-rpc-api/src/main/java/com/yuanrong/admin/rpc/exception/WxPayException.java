package com.yuanrong.admin.rpc.exception;

/**
 * Created by zhonghang on 2018/7/13.
 * 微信支付异常
 */
public class WxPayException  extends RuntimeException {
    private String code;
    private String msg;
    public WxPayException(String message) {
        super(message);
    }
    public WxPayException(){}
    public WxPayException(String code ,String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
