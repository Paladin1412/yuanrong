package com.yuanrong.admin.result.wxPay;

import java.io.Serializable;

/**
 * Created by zhonghang on 2018/11/05.
 */
public class WxPayOrder implements Serializable {
    private String outTradeNo;
    private String tradeState;

    public WxPayOrder(){}

    public WxPayOrder(String outTradeNo, String tradeState) {
        this.outTradeNo = outTradeNo;
        this.tradeState = tradeState;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }
}
