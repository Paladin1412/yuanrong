package com.yuanrong.admin.bean.order;
import java.util.*;
import java.io.Serializable;
import java.math.*;
import com.yuanrong.admin.bean.BaseBean;

/**
 * 卖家订单报价的实体类
 *
 * @author MDA
 *
 */
public class OrderInfoOffer extends BaseBean implements Serializable {
    // Fields
    private static final long serialVersionUID = 1L;
     
    /**
     * 主键，自增类型
     */
    private Integer orderInfoOfferId;
    private Integer[] orderInfoOfferIds;
    /*****自定义属性区域begin******/
   
   
    /**
     * 订单ID(买家订单id或者卖家订单id)
     * 订单ID(买家订单id或者卖家订单id)
     */
    private Integer orderInfoSeller;
   
    /**
     * 报价名称
     * 报价名称
     */
    private String priceName;
    private String[] priceNames;
   
    /**
     * 应约价
     * 应约价
     */
    private BigDecimal accedePrice;
    private BigDecimal[] accedePrices;

    /**
     * 应约价
     */
    private BigDecimal price;
    private BigDecimal[] prices;

    /**
     * 服务费
     */
    private String serviceRate;

    /**
     * 执行价
     * @return
     */
    private BigDecimal executePrice;
    private BigDecimal[] executePrices;

    private Integer[] statuss;
    private BigDecimal sellerPrice;
    public Integer getOrderInfoOfferId() {
        return this.orderInfoOfferId;
    }
    public void setOrderInfoOfferId(Integer orderInfoOfferId) {
        this.orderInfoOfferId = orderInfoOfferId;
    }
    /*****自定义属性区域begin.get/set******/
   
    public Integer getOrderInfoSeller() {
        return this.orderInfoSeller;
    }
    public void setOrderInfoSeller(Integer orderInfoSeller) {
        this.orderInfoSeller = orderInfoSeller;
    }
   
    public String getPriceName() {
        return this.priceName;
    }
    public void setPriceName(String priceName) {
        this.priceName = priceName;
    }
    public BigDecimal getPrice() {
        return this.price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String[] getPriceNames() {
        return priceNames;
    }

    public void setPriceNames(String[] priceNames) {
        this.priceNames = priceNames;
    }

    public BigDecimal[] getPrices() {
        return prices;
    }

    public void setPrices(BigDecimal[] prices) {
        this.prices = prices;
    }

    public Integer[] getOrderInfoOfferIds() {
        return orderInfoOfferIds;
    }

    public void setOrderInfoOfferIds(Integer[] orderInfoOfferIds) {
        this.orderInfoOfferIds = orderInfoOfferIds;
    }

    public String getServiceRate() {
        return serviceRate;
    }

    public void setServiceRate(String serviceRate) {
        this.serviceRate = serviceRate;
    }

    public BigDecimal getAccedePrice() {
        return accedePrice;
    }

    public void setAccedePrice(BigDecimal accedePrice) {
        this.accedePrice = accedePrice;
    }

    public BigDecimal[] getAccedePrices() {
        return accedePrices;
    }

    public void setAccedePrices(BigDecimal[] accedePrices) {
        this.accedePrices = accedePrices;
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

    public Integer[] getStatuss() {
        return statuss;
    }

    public void setStatuss(Integer[] statuss) {
        this.statuss = statuss;
    }

    public BigDecimal getSellerPrice() {
        return sellerPrice;
    }

    public void setSellerPrice(BigDecimal sellerPrice) {
        this.sellerPrice = sellerPrice;
    }
}
