package com.yuanrong.admin.seach;

import com.yuanrong.admin.bean.order.OrderInfoBuyer;

import java.io.Serializable;

/**
 * 创作者列表查询参数
 */
public class OrderInfoBuyerSearch extends OrderInfoBuyer implements Serializable {

    /**
     * 作品名称
     */
    private String production;

    /**
     * 创建开始时间
     */
    private String startTime;
    /**
     * 创建结束时间
     */
    private String endTime;
    /**
     * 作品类型
     */
    private Integer publishStatusValue;


    /**
     * 购物车IDs
     */
    private Integer[] shoppingCartIds;


    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getPublishStatusValue() {
        return publishStatusValue;
    }

    public void setPublishStatusValue(Integer publishStatusValue) {
        this.publishStatusValue = publishStatusValue;
    }

    public Integer[] getShoppingCartIds() {
        return shoppingCartIds;
    }

    public void setShoppingCartIds(Integer[] shoppingCartIds) {
        this.shoppingCartIds = shoppingCartIds;
    }
}