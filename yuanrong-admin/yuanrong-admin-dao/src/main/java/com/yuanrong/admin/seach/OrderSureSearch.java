package com.yuanrong.admin.seach;

import com.yuanrong.admin.bean.BaseBean;

import java.io.Serializable;

/**
 * 确认订单
 */
public class OrderSureSearch extends BaseBean implements Serializable {
    /**
     * 流水号（表名表）
     */
    private  String[]  orderSns;

    /**
     * 需求Ids
     */
    private Integer[] demandIds;

    /**
     * 作品ID
     */
    private Integer yrProductionId;

    /**
     * 购物车Ids
     */
    private Integer[] shoppingCarts;

    /**
     * 确定订单类型
     * （1）流水单号、（2）立即下单、（3）购物车
     */
    private Integer type;

    public Integer[] getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(Integer[] shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    public Integer getYrProductionId() {
        return yrProductionId;
    }

    public void setYrProductionId(Integer yrProductionId) {
        this.yrProductionId = yrProductionId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String[] getOrderSns() {
        return orderSns;
    }

    public void setOrderSns(String[] orderSns) {
        this.orderSns = orderSns;
    }

    public Integer[] getDemandIds() {
        return demandIds;
    }

    public void setDemandIds(Integer[] demandIds) {
        this.demandIds = demandIds;
    }
}
