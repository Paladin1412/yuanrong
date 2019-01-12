package com.yuanrong.admin.bean.pay;
import java.util.*;
import java.io.Serializable;
import java.math.*;

import com.yuanrong.admin.Enum.EnumPayType;
import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.util.EnumUtil;

/**
 * 支付流水的实体类
 *
 * @author MDA
 *
 */
public class PayWater extends BaseBean implements Serializable {
    // Fields
    private static final long serialVersionUID = 1L;
     
    /**
     * 主键，自增类型
     */
    private int payWaterId;
    /*****自定义属性区域begin******/
   
   
    /**
     * 商户订单号
     * 商户订单号
     */
    private String outTradeNo;
   
    /**
     * 微信支付订单号
     * 微信支付订单号
     */
    private String transactionId;
   
    /**
     * 订单金额
     * 订单金额
     */
    private BigDecimal totalFee;
   
    /**
     * 用户标识
     * 用户标识
     */
    private String openid;
   
    /**
     * 是否关注公众账号
     * 是否关注公众账号
     */
    private String isSubscribe;
   
    /**
     * 交易类型
     * 交易类型
     */
    private String tradeType;
   
    /**
     * 付款银行
     * 付款银行
     */
    private String bankType;
   
    /**
     * 支付完成时间
     * 支付完成时间
     */
    private String timeEnd;
   
    /**
     * 商户号
     * 商户号
     */
    private String mchId;

    /**
     * 支付方式
     */
    private EnumPayType payType;
    private Integer payTypeIndex;

    /**
     * 付款用户ID
     */
    private Integer registeredUserInfoId;

    public int getPayWaterId() {
        return this.payWaterId;
    }
    public void setPayWaterId(int payWaterId) {
        this.payWaterId = payWaterId;
    }
    /*****自定义属性区域begin.get/set******/
   
    public String getOutTradeNo() {
        return this.outTradeNo;
    }
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
    public String getTransactionId() {
        return this.transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public BigDecimal getTotalFee() {
        return this.totalFee;
    }
    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }
    public String getOpenid() {
        return this.openid;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public String getIsSubscribe() {
        return this.isSubscribe;
    }
    public void setIsSubscribe(String isSubscribe) {
        this.isSubscribe = isSubscribe;
    }
    public String getTradeType() {
        return this.tradeType;
    }
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }
    public String getBankType() {
        return this.bankType;
    }
    public void setBankType(String bankType) {
        this.bankType = bankType;
    }
    public String getTimeEnd() {
        return this.timeEnd;
    }
    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }
    public String getMchId() {
        return this.mchId;
    }
    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public EnumPayType getPayType() {
        return payType;
    }

    public void setPayType(EnumPayType payType) {
        this.payType = payType;
        this.payTypeIndex = payType.getIndex();
    }

    public Integer getPayTypeIndex() {
        return payTypeIndex;
    }

    public void setPayTypeIndex(Integer payTypeIndex) {
        this.payTypeIndex = payTypeIndex;
        this.payType = (EnumPayType) EnumUtil.valueOf(EnumPayType.class , payTypeIndex);
    }

    public Integer getRegisteredUserInfoId() {
        return registeredUserInfoId;
    }

    public void setRegisteredUserInfoId(Integer registeredUserInfoId) {
        this.registeredUserInfoId = registeredUserInfoId;
    }
}
