package com.yuanrong.admin.seach;

import com.yuanrong.admin.bean.BaseBean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;

/**
 * 订单管理查询条件
 */
public class OrderManagementSearch extends BaseBean implements Serializable {
    /**
     * 账号名
     */
    private String name;
    private String[] names;
    /**
     * 账号ID
     */
    private String accountID;
    private String[] accountIDs;
    /**
     * 订单号
     */
    private String orderSn;
    private String[] orderSns;
    /**
     * 订单状态
     */
    private Integer orderStatusValue;
    /**
     * 订单类型
     */
    private Integer orderTypeValue;
    /**
     * 媒介经理
     */
    private String adminUserMediaID;
    private String[] adminUserMediaIDs;

    /**
     * userId
     */
    private Integer registeredUserInfoId;
    private Integer[] registeredUserInfoIds;

    /**
     * 用户简称
     */
    private String nickName;
    private String[] nickNames;

    /**
     * 创建开始时间
     */
    private String startCreatedTime;

    /**
     * 创建结束时间
     */
    private String endCreatedTime;

    /**
     * 开始执行时间
     */
    private String startExecuteTime;
    /**
     * 结束执行时间
     */
    private String endExecuteTime;

    /**
     * 响应开始时间
     */
    private String startResponseTime;

    /**
     * 响应结束时间
     */
    private String endResponseTime;

    private static final long serialVersionUID = 1L;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Integer getOrderStatusValue() {
        return orderStatusValue;
    }

    public void setOrderStatusValue(Integer orderStatusValue) {
        this.orderStatusValue = orderStatusValue;
    }

    public Integer getOrderTypeValue() {
        return orderTypeValue;
    }

    public void setOrderTypeValue(Integer orderTypeValue) {
        this.orderTypeValue = orderTypeValue;
    }

    public String getAdminUserMediaID() {
        return adminUserMediaID;
    }

    public void setAdminUserMediaID(String adminUserMediaID) {
        this.adminUserMediaID = adminUserMediaID;
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

    public String getStartCreatedTime() {
        return startCreatedTime;
    }

    public void setStartCreatedTime(String startCreatedTime) {
        this.startCreatedTime = startCreatedTime;
    }

    public String getEndCreatedTime() {
        return endCreatedTime;
    }

    public void setEndCreatedTime(String endCreatedTime) {
        this.endCreatedTime = endCreatedTime;
    }

    public String getStartExecuteTime() {
        return startExecuteTime;
    }

    public void setStartExecuteTime(String startExecuteTime) {
        this.startExecuteTime = startExecuteTime;
    }

    public String getEndExecuteTime() {
        return endExecuteTime;
    }

    public void setEndExecuteTime(String endExecuteTime) {
        this.endExecuteTime = endExecuteTime;
    }

    public String getStartResponseTime() {
        return startResponseTime;
    }

    public void setStartResponseTime(String startResponseTime) {
        this.startResponseTime = startResponseTime;
    }

    public String getEndResponseTime() {
        return endResponseTime;
    }

    public void setEndResponseTime(String endResponseTime) {
        this.endResponseTime = endResponseTime;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public String[] getAccountIDs() {
        return accountIDs;
    }

    public void setAccountIDs(String[] accountIDs) {
        this.accountIDs = accountIDs;
    }

    public String[] getOrderSns() {
        return orderSns;
    }

    public void setOrderSns(String[] orderSns) {
        this.orderSns = orderSns;
    }

    public String[] getAdminUserMediaIDs() {
        return adminUserMediaIDs;
    }

    public void setAdminUserMediaIDs(String[] adminUserMediaIDs) {
        this.adminUserMediaIDs = adminUserMediaIDs;
    }

    public Integer[] getRegisteredUserInfoIds() {
        return registeredUserInfoIds;
    }

    public void setRegisteredUserInfoIds(Integer[] registeredUserInfoIds) {
        this.registeredUserInfoIds = registeredUserInfoIds;
    }

    public String[] getNickNames() {
        return nickNames;
    }

    public void setNickNames(String[] nickNames) {
        this.nickNames = nickNames;
    }

    @Override
    public String toString() {
        return "OrderManagementSearch{" +
                "name='" + name + '\'' +
                ", names=" + Arrays.toString(names) +
                ", accountID='" + accountID + '\'' +
                ", accountIDs=" + Arrays.toString(accountIDs) +
                ", orderSn=" + orderSn +
                ", orderSns=" + Arrays.toString(orderSns) +
                ", orderStatusValue=" + orderStatusValue +
                ", orderTypeValue=" + orderTypeValue +
                ", adminUserMediaID='" + adminUserMediaID + '\'' +
                ", adminUserMediaIDs=" + Arrays.toString(adminUserMediaIDs) +
                ", registeredUserInfoId=" + registeredUserInfoId +
                ", registeredUserInfoIds=" + Arrays.toString(registeredUserInfoIds) +
                ", nickName=" + nickName +
                ", nickNames=" + Arrays.toString(nickNames) +
                ", startCreatedTime=" + startCreatedTime +
                ", endCreatedTime=" + endCreatedTime +
                ", startExecuteTIme=" + startExecuteTime +
                ", endExecuteTIme=" + endExecuteTime +
                ", startResponseTime=" + startResponseTime +
                ", endResponseTime=" + endResponseTime +
                '}';
    }
}