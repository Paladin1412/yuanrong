package com.yuanrong.admin.seach;

import com.yuanrong.admin.bean.BaseBean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 创作者列表查询参数
 */
public class OrderPriceParamSearch extends BaseBean implements Serializable {
    /**
     * 报名记录ID
     */
    private String orderSn;
    /**
     * 应约记录ID
     */
    private Integer[] offerIds;
    /**
     * 订单报价
     */
    private BigDecimal sellerPrice;
    private BigDecimal[] sellerPrices;
    /**
     * 买家应约价+服务费
     */
    private BigDecimal executePrice;
    private BigDecimal[] executePrices;
    /**
     * 订单收入
     */
    private BigDecimal payAble;
    private BigDecimal[] payAbles;
    /**
     * 卖家佣金
     */
    private BigDecimal sellerServiceRate;
    /**
     * 买家服务费率
     */
    private BigDecimal buyerServiceRate;
    /**
     * 类别
     */
    private Integer orderStatus;
    /**
     * 应约价
     */
    private BigDecimal price;
    private BigDecimal[] prices;

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public BigDecimal getPayAble() {
        return payAble;
    }

    public void setPayAble(BigDecimal payAble) {
        this.payAble = payAble;
    }

    public BigDecimal getSellerServiceRate() {
        return sellerServiceRate;
    }

    public void setSellerServiceRate(BigDecimal sellerServiceRate) {
        this.sellerServiceRate = sellerServiceRate;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getSellerPrice() {
        return sellerPrice;
    }

    public void setSellerPrice(BigDecimal sellerPrice) {
        this.sellerPrice = sellerPrice;
    }

    public BigDecimal[] getSellerPrices() {
        return sellerPrices;
    }

    public void setSellerPrices(BigDecimal[] sellerPrices) {
        this.sellerPrices = sellerPrices;
    }

    public BigDecimal[] getPayAbles() {
        return payAbles;
    }

    public void setPayAbles(BigDecimal[] payAbles) {
        this.payAbles = payAbles;
    }

    public Integer[] getOfferIds() {
        return offerIds;
    }

    public void setOfferIds(Integer[] offerIds) {
        this.offerIds = offerIds;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal[] getPrices() {
        return prices;
    }

    public void setPrices(BigDecimal[] prices) {
        this.prices = prices;
    }

    public BigDecimal getBuyerServiceRate() {
        return buyerServiceRate;
    }

    public void setBuyerServiceRate(BigDecimal buyerServiceRate) {
        this.buyerServiceRate = buyerServiceRate;
    }

    public BigDecimal getExecutePrice() {
        return executePrice;
    }

    public void setExecutePrice(BigDecimal executePrice) {
        this.executePrice = executePrice;
    }

    public BigDecimal[] getExecutePrices() {
        return executePrices;
    }

    public void setExecutePrices(BigDecimal[] executePrices) {
        this.executePrices = executePrices;
    }
}