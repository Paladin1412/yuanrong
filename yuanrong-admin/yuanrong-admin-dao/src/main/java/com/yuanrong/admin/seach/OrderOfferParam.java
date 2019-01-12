package com.yuanrong.admin.seach;

import com.yuanrong.admin.bean.order.OrderInfoSeller;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单报价（账号报价/创作者报价）
 */
public class OrderOfferParam extends OrderInfoSeller implements Serializable {
    private String priceName;
    /**
     * 报价名数组
     */
    private String[] priceNames;
    /**
     * 价格数组
     */
    private String[] prices;
    /**
     * 账号Id/创作者ID
     */
    private Integer productId;

    /**
     * 服务费
     */
    private String serviceFee;


    private Integer orderInfoOfferId;
    private BigDecimal executePrice;
    /**
     * 订单金额
     */
    private BigDecimal[] costMoney;
    /**
     * 价格项Id
     */
    private Integer[] offerIds;
    /**
     * 标记
     */
    private Integer flag;
    /**
     * 报名ID
     */
    private Integer sellerOrderId;
    private static final long serialVersionUID = 1L;

    public String[] getPriceNames() {
        return priceNames;
    }

    public void setPriceNames(String[] priceNames) {
        this.priceNames = priceNames;
    }

    public String[] getPrices() {
        return prices;
    }

    public void setPrices(String[] prices) {
        this.prices = prices;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Integer getOrderInfoOfferId() {
        return orderInfoOfferId;
    }

    public void setOrderInfoOfferId(Integer orderInfoOfferId) {
        this.orderInfoOfferId = orderInfoOfferId;
    }

    public BigDecimal getExecutePrice() {
        return executePrice;
    }

    public void setExecutePrice(BigDecimal executePrice) {
        this.executePrice = executePrice;
    }

    public String getPriceName() {
        return priceName;
    }

    public void setPriceName(String priceName) {
        this.priceName = priceName;
    }

    public Integer[] getOfferIds() {
        return offerIds;
    }

    public void setOfferIds(Integer[] offerIds) {
        this.offerIds = offerIds;
    }

    public BigDecimal[] getCostMoney() {
        return costMoney;
    }

    public void setCostMoney(BigDecimal[] costMoney) {
        this.costMoney = costMoney;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getSellerOrderId() {
        return sellerOrderId;
    }

    public void setSellerOrderId(Integer sellerOrderId) {
        this.sellerOrderId = sellerOrderId;
    }
}