package com.yuanrong.admin.seach;

import com.yuanrong.admin.bean.BaseBean;
import com.yuanrong.admin.bean.order.OrderInfoBuyer;

import java.io.Serializable;

/**
 * 后台作品购买列表——查询参数
 */
public class OrderInfoBuyerListSearch extends BaseBean implements Serializable {

    /**
     * 订单号
     */
    private String[] orderSn;
    /**
     * 支付状态
     */
    private Integer payStatusValue;
    /**
     * 用户Id
     */
    private Integer userId;
    /**
     * 销售经理
     */
    private Integer saleId;
    /**
     * 注册手机号
     */
    private String[] mobile;
    /**
     * 创建时间—开始
     */
    private String createdTimeBegin;
    /**
     * 创建时间—结束
     */
    private String createdTimeEnd;
    /**
     * 媒介经理
     */
    private Integer mediaId;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 支付时间—Begin
     */
    private String payTimeBegin;
    /**
     * 支付时间—End
     */
    private String payTimeEnd;
    /**
     * 买家用户
     */
    private Integer buyerId;
    /**
     * 卖家用户
     */
    private Integer sellerId;
    public String[] getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String[] orderSn) {
        this.orderSn = orderSn;
    }

    public Integer getPayStatusValue() {
        return payStatusValue;
    }

    public void setPayStatusValue(Integer payStatusValue) {
        this.payStatusValue = payStatusValue;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public String[] getMobile() {
        return mobile;
    }

    public void setMobile(String[] mobile) {
        this.mobile = mobile;
    }

    public String getCreatedTimeBegin() {
        return createdTimeBegin;
    }

    public void setCreatedTimeBegin(String createdTimeBegin) {
        this.createdTimeBegin = createdTimeBegin;
    }

    public String getCreatedTimeEnd() {
        return createdTimeEnd;
    }

    public void setCreatedTimeEnd(String createdTimeEnd) {
        this.createdTimeEnd = createdTimeEnd;
    }

    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPayTimeBegin() {
        return payTimeBegin;
    }

    public void setPayTimeBegin(String payTimeBegin) {
        this.payTimeBegin = payTimeBegin;
    }

    public String getPayTimeEnd() {
        return payTimeEnd;
    }

    public void setPayTimeEnd(String payTimeEnd) {
        this.payTimeEnd = payTimeEnd;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }
}