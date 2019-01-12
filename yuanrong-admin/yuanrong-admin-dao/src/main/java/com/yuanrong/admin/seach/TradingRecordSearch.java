package com.yuanrong.admin.seach;

import com.yuanrong.admin.bean.trading.TradingRecord;

import java.io.Serializable;
/**
* @Description:    
* @Author:         ShiLinghuai
* @CreateDate:     2018/8/20 9:55
* @UpdateUser:     ShiLinghuai
* @UpdateDate:     2018/8/20 9:55
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class TradingRecordSearch extends TradingRecord implements Serializable{
    /**
     * 用户简称
     */
    private String[] nickNames;
    /**
     * 执行时间
     */
    private String tradingDateStart;
    /**
     *
     */
    private String tradingDateEnd;
    /**
     * 上传时间
     */
    private String createdTimeStart;
    private String createdTimeEnd;
    /**
     * 用户id
     */
    private String[] registeredUserInfoIds;
    /**
     * 账号名
     */
    private String[] sellerAccounts;
    //交易所涉平台
    private String[] referPlatforms;

    /**
    *批量操作要做什么
     */
    private Integer todo;
    /**
     * 交易记录id
     */
    private Integer[] tradingRecordIds;
    /**
     * 合作品牌
     */
    private String[] cooPerationBrands;
    /**
     * 买方
     */
    private String[] buyerNames;

    public String[] getBuyerNames() {
        return buyerNames;
    }

    public void setBuyerNames(String[] buyerNames) {
        this.buyerNames = buyerNames;
    }

    public String[] getCooPerationBrands() {
        return cooPerationBrands;
    }

    public void setCooPerationBrands(String[] cooPerationBrands) {
        this.cooPerationBrands = cooPerationBrands;
    }

    public Integer[] getTradingRecordIds() {
        return tradingRecordIds;
    }

    public void setTradingRecordIds(Integer[] tradingRecordIds) {
        this.tradingRecordIds = tradingRecordIds;
    }

    public Integer getTodo() {
        return todo;
    }

    public void setTodo(Integer todo) {
        this.todo = todo;
    }

    public String[] getReferPlatforms() {
        return referPlatforms;
    }

    public void setReferPlatforms(String[] referPlatforms) {
        this.referPlatforms = referPlatforms;
    }

    public String[] getSellerAccounts() {
        return sellerAccounts;
    }

    public void setSellerAccounts(String[] sellerAccounts) {
        this.sellerAccounts = sellerAccounts;
    }

    public String[] getRegisteredUserInfoIds() {
        return registeredUserInfoIds;
    }

    public void setRegisteredUserInfoIds(String[] registeredUserInfoIds) {
        this.registeredUserInfoIds = registeredUserInfoIds;
    }

    public String[] getNickNames() {
        return nickNames;
    }

    public void setNickNames(String[] nickNames) {
        this.nickNames = nickNames;
    }



    public String getTradingDateStart() {
        return tradingDateStart;
    }

    public void setTradingDateStart(String tradingDateStart) {
        this.tradingDateStart = tradingDateStart;
    }

    public String getTradingDateEnd() {
        return tradingDateEnd;
    }

    public void setTradingDateEnd(String tradingDateEnd) {
        this.tradingDateEnd = tradingDateEnd;
    }

    public String getCreatedTimeStart() {
        return createdTimeStart;
    }

    public void setCreatedTimeStart(String createdTimeStart) {
        this.createdTimeStart = createdTimeStart;
    }

    public String getCreatedTimeEnd() {
        return createdTimeEnd;
    }

    public void setCreatedTimeEnd(String createdTimeEnd) {
        this.createdTimeEnd = createdTimeEnd;
    }
}
