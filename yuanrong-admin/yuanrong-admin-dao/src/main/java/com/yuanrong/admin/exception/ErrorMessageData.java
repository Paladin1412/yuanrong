package com.yuanrong.admin.exception;

public class ErrorMessageData {
    public static final String CODE = "code";
    public static final String MSG = "msg";

    private String code;
    private String msg;

    public ErrorMessageData() {}
    public ErrorMessageData(String code,String msg) {
        this.setCode(code);
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
        //this.msg = PropertiesUtil.get("code", MSG+code);
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
