package com.yuanrong.admin.seach;

import java.io.Serializable;
import java.util.Date;

/*
 *@author songwq
 *@date 2018/9/21
 *@time 16:55
 *@description
 */
public class SellerOrderSearch implements Serializable {

    //报名号
    private String serialOrderSn;

    //订单号
    private String orderSn;

    private Integer sellerOrderType;

    private Integer registeredUserInfoId;

    private String nickName;

    private Date startCreatedTime;

    private Date endCreatedTime;

    private String[] mediaUser;

    private String[] names;

    private Integer orderStatus;

    private Integer orderInfoSellerId;

    public String getSerialOrderSn() {
        return serialOrderSn;
    }

    public void setSerialOrderSn(String serialOrderSn) {
        this.serialOrderSn = serialOrderSn;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Integer getSellerOrderType() {
        return sellerOrderType;
    }

    public void setSellerOrderType(Integer sellerOrderType) {
        this.sellerOrderType = sellerOrderType;
    }

    public Integer getRegisteredUserInfoId() {
        return registeredUserInfoId;
    }

    public void setRegisteredUserInfoId(Integer registeredUserInfoId) {
        this.registeredUserInfoId = registeredUserInfoId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getStartCreatedTime() {
        return startCreatedTime;
    }

    public void setStartCreatedTime(Date startCreatedTime) {
        this.startCreatedTime = startCreatedTime;
    }

    public Date getEndCreatedTime() {
        return endCreatedTime;
    }

    public void setEndCreatedTime(Date endCreatedTime) {
        this.endCreatedTime = endCreatedTime;
    }

    public String[] getMediaUser() {
        return mediaUser;
    }

    public void setMediaUser(String[] mediaUser) {
        this.mediaUser = mediaUser;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderInfoSellerId() {
        return orderInfoSellerId;
    }

    public void setOrderInfoSellerId(Integer orderInfoSellerId) {
        this.orderInfoSellerId = orderInfoSellerId;
    }
}
